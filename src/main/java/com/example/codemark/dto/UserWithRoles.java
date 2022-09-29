package com.example.codemark.dto;

import java.util.List;

public class UserWithRoles {

    private UserDTO user;
    private List<String> roleList;


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
