package com.example.codemark.service;

import com.example.codemark.dto.ResponseTemplate;
import com.example.codemark.dto.UserDTO;
import com.example.codemark.dto.UserWithRoles;
import com.example.codemark.entity.Role;
import com.example.codemark.entity.User;
import com.example.codemark.entity.User2Role;
import com.example.codemark.repository.RoleRepository;
import com.example.codemark.repository.User2RoleRepository;
import com.example.codemark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@WebService(endpointInterface = "com.example.codemark.service.UserService", targetNamespace = "http://com.testTask.userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private User2RoleRepository user2RoleRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setLogin(user.getLogin());
            userDTO.setName(user.getName());
            userDTO.setPassword(user.getPassword());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public UserWithRoles getUserByLogin(String login) {
        UserWithRoles userWithRoles = new UserWithRoles();
        User user = userRepository.findUserByLogin(login);
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setLogin(user.getLogin());
            userDTO.setName(user.getName());
            userDTO.setPassword(user.getPassword());
            userWithRoles.setUser(userDTO);
            List<Role> roles = roleRepository.findRolesByUserLogin(login);
            if (roles != null) {
                List<String> roleList = roles.stream().map(Role::getName).collect(Collectors.toList());
                userWithRoles.setRoleList(roleList);
            }
        }
        return userWithRoles;
    }

    @Override
    public ResponseTemplate deleteUser(String login) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        User userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin != null) {
            user2RoleRepository.deleteAll(user2RoleRepository.findUser2RoleByLogin(login));
            userRepository.delete(userByLogin);
            responseTemplate.setSuccess(true);
        } else {
            List<String> errors = new ArrayList<>();
            errors.add("пользователя с таким login не существует");
            responseTemplate.setSuccess(false);
            responseTemplate.setErrors(errors);
        }
        return responseTemplate;
    }

    @Override
    public ResponseTemplate addUser(String login, String name, String password, List<String> roleList) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        List<String> errors = new ArrayList<>();
        addErrors(login, name, password, errors, true);
        if (errors.size() > 0) {
            responseTemplate.setSuccess(false);
            responseTemplate.setErrors(errors);
        } else {
            User user = new User();
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            userRepository.save(user);

            if (roleList != null) {
                addRolesAfterCheck(roleList, user);
            }
            responseTemplate.setSuccess(true);
        }
        return responseTemplate;
    }

    @Override
    public ResponseTemplate updateUser(String login, String name, String password, List<String> roleList) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        List<String> errors = new ArrayList<>();
        addErrors(login, name, password, errors, false);
        if (errors.size() > 0) {
            responseTemplate.setSuccess(false);
            responseTemplate.setErrors(errors);
        } else {
            User user = userRepository.findUserByLogin(login);
            user.setName(name);
            user.setPassword(password);
            userRepository.save(user);

            //если список ролей не пуст, то необходимо все старые роли пользователя убрать и добавить новые
            if (roleList != null) {
                //сначала удалим старые связи пользователя с ролями
                user2RoleRepository.deleteAll(user2RoleRepository.findUser2RoleByLogin(login));
                //добавим новые связи
                addRolesAfterCheck(roleList, user);
            }
            responseTemplate.setSuccess(true);
        }
        return responseTemplate;
    }

    //вспомогательные методы
    public void addRolesAfterCheck(List<String> roleList, User user) {
        for (String roleFrom : roleList) {
            //если такой роли в БД нет, то добавляем ее
            if (roleRepository.findRoleByName(roleFrom) == null) {
                Role role = new Role();
                role.setName(roleFrom);
                roleRepository.save(role);
                //и добавляем связь
                User2Role user2Role = new User2Role();
                user2Role.setUser(user);
                user2Role.setRole(role);
                user2RoleRepository.save(user2Role);
            } else {
                //если такая роль в БД есть, то роль не добавляем, а добавляем связь с одноименной ролью из БД
                User2Role user2Role = new User2Role();
                user2Role.setUser(user);
                user2Role.setRole(roleRepository.findRoleByName(roleFrom));
                user2RoleRepository.save(user2Role);
            }
        }
    }

    public void addErrors(String login, String name, String password, List<String> errors, boolean checkAddUserMethod) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(password);
        if (login.isEmpty()) errors.add("поле login не должно быть пустым");
        if (name.isEmpty()) errors.add("поле name не должно быть пустым");
        if (password.isEmpty()) errors.add("поле password не должно быть пустым");
        if (!matcher.find()) errors.add("поле password должно содержать хотя бы одно числовое значение");
        if (password.equals(password.toLowerCase())) errors.add("поле password должно содержать хотя бы одну заглавную букву");
        //в зависимости от метода нам необходимо проверятб существует ли данный пользователь в БД или нет
        if (checkAddUserMethod) {
            if (userRepository.findUserByLogin(login) != null) errors.add("невозможно добавить пользователя с такми login. Пользователь с таким login уже существует");
        } else {
            if (userRepository.findUserByLogin(login) == null) errors.add("невозможно произвести update. Пользователя с таким login не существует");
        }
    }
}
