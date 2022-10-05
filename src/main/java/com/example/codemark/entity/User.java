package com.example.codemark.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String login;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user2role",
            joinColumns = @JoinColumn(name = "user_login"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> user2role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getUser2role() {
        return user2role;
    }

    public void setUser2role(List<Role> user2role) {
        this.user2role = user2role;
    }
}
