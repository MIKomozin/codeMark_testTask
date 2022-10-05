package com.example.codemark.service;

import com.example.codemark.dto.ResponseTemplate;
import com.example.codemark.dto.UserDTO;
import com.example.codemark.dto.UserWithRoles;
import com.example.codemark.entity.Role;
import com.example.codemark.entity.User;
import com.example.codemark.repository.RoleRepository;
import com.example.codemark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//реализация веб-сервиса
@Component
@WebService(endpointInterface = "com.example.codemark.service.UserService", targetNamespace = "http://com.testTask.userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
            List<Role> roles = user.getUser2role();
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
        if (userRepository.findUserByLogin(login) != null) {
            errors.add("невозможно добавить пользователя с такми login. Пользователь с таким login уже существует");
        }
        checkInputParameters(login, name, password, errors);
        if (errors.size() > 0) {
            responseTemplate.setSuccess(false);
            responseTemplate.setErrors(errors);
        } else {
            User user = new User();
            user.setLogin(login);
            user.setName(name);
            user.setPassword(password);
            //если список ролей не пуст, то добавим связи юзеру
            if (roleList != null) {
                addRolesAfterCheck(roleList, user);
            }
            userRepository.save(user);
            responseTemplate.setSuccess(true);
        }
        return responseTemplate;
    }

    @Override
    public ResponseTemplate updateUser(String login, String name, String password, List<String> roleList) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        List<String> errors = new ArrayList<>();
        if (userRepository.findUserByLogin(login) == null) {
            errors.add("невозможно произвести update. Пользователя с таким login не существует");
        }
        checkInputParameters(login, name, password, errors);
        if (errors.size() > 0) {
            responseTemplate.setSuccess(false);
            responseTemplate.setErrors(errors);
        } else {
            User user = userRepository.findUserByLogin(login);
            user.setName(name);
            user.setPassword(password);
            //если список ролей не пуст, то обновим роли юзера
            if (roleList != null) {
                addRolesAfterCheck(roleList, user);
            }
            userRepository.save(user);
            responseTemplate.setSuccess(true);
        }
        return responseTemplate;
    }

    //вспомогательные методы
    //метод для проверки введенных ролей на их уже возможное существование в БД (во избежании дублирования ролей)
    public void addRolesAfterCheck(List<String> roleList, User user) {
        List<Role> roles = new ArrayList<>();
        for (String roleFrom : roleList) {
            Role checkRole = roleRepository.findRoleByName(roleFrom);
            //если такой роли в БД нет, то добавляем ее
            if (checkRole == null) {
                Role role = new Role();
                role.setName(roleFrom);
                roleRepository.save(role);
                //и добавляем связь
                roles.add(role);
            } else {
                //если такая роль в БД есть, то роль не добавляем, а добавляем связь с одноименной ролью из БД
                roles.add(checkRole);
            }
        }
        user.setUser2role(roles);
    }


    public void checkInputParameters(String login, String name, String password, List<String> errors) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(password);
        if (login.isEmpty()) errors.add("поле login не должно быть пустым");
        if (name.isEmpty()) errors.add("поле name не должно быть пустым");
        if (password.isEmpty()) errors.add("поле password не должно быть пустым");
        if (!matcher.find()) errors.add("поле password должно содержать хотя бы одно числовое значение");
        if (password.equals(password.toLowerCase())) errors.add("поле password должно содержать хотя бы одну заглавную букву");
    }
}