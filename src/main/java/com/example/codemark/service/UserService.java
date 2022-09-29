package com.example.codemark.service;

import com.example.codemark.dto.ResponseTemplate;
import com.example.codemark.dto.UserDTO;
import com.example.codemark.dto.UserWithRoles;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@Component
@WebService
public interface UserService {

    @WebMethod
    @RequestWrapper(localName = "getAllUsersRequest", targetNamespace = "http://com.testTask.userService")
    @ResponseWrapper(localName = "getAllUsersResponse", className = "com.example.codemark.response.GetAllUsersResponse",
            targetNamespace = "http://com.testTask.userService")
    @WebResult(name = "returnUsers", targetNamespace = "http://com.testTask.userService")
    List<UserDTO> getAllUsers();

    @WebMethod
    @RequestWrapper(localName = "getUserByLoginRequest", targetNamespace = "http://com.testTask.userService")
    @ResponseWrapper(localName = "getUserByLoginResponse", className = "com.example.codemark.response.GetUserByLoginResponse",
            targetNamespace = "http://com.testTask.userService")
    @WebResult(name = "returnUserWithRoles", targetNamespace = "http://com.testTask.userService")
    UserWithRoles getUserByLogin(@WebParam(name = "login") String login);

    @WebMethod
    @RequestWrapper(localName = "deleteUserRequest", targetNamespace = "http://com.testTask.userService")
    @ResponseWrapper(localName = "deleteUserResponse", className = "com.example.codemark.response.DeleteUserResponse",
            targetNamespace = "http://com.testTask.userService")
    @WebResult(name = "returnDeleteResponse", targetNamespace = "http://com.testTask.userService")
    ResponseTemplate deleteUser(@WebParam(name = "login") String login);

    @WebMethod
    @RequestWrapper(localName = "addUserRequest", targetNamespace = "http://com.testTask.userService")
    @ResponseWrapper(localName = "addUserResponse", className = "com.example.codemark.response.AddUserResponse",
            targetNamespace = "http://com.testTask.userService")
    @WebResult(name = "returnAddResponse", targetNamespace = "http://com.testTask.userService")
    ResponseTemplate addUser(@WebParam(name = "login") String login,
                             @WebParam(name = "name") String name,
                             @WebParam(name = "password") String password,
                             @WebParam(name = "roleList") List<String> roleList);

    @WebMethod
    @RequestWrapper(localName = "updateUserRequest", targetNamespace = "http://com.testTask.userService")
    @ResponseWrapper(localName = "updateUserResponse", className = "com.example.codemark.response.UpdateUserResponse",
            targetNamespace = "http://com.testTask.userService")
    @WebResult(name = "returnUpdateResponse", targetNamespace = "http://com.testTask.userService")
    ResponseTemplate updateUser(@WebParam(name = "login") String login,
                                @WebParam(name = "name") String name,
                                @WebParam(name = "password") String password,
                                @WebParam(name = "roleList") List<String> roleList);
}
