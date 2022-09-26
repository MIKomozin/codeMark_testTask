package com.example.codemark.entity;

import java.io.Serializable;

public class User2RoleId implements Serializable {

    private String user;
    private int role;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
