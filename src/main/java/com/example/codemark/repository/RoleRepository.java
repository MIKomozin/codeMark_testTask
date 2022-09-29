package com.example.codemark.repository;

import com.example.codemark.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //получить конкрутную роль по name (идентификатор поиска)
    @Query(value = "SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
    Role findRoleByName(String name);

    //получить все роли пользователя по login
    @Query(value = "SELECT roles.id AS id, roles.name AS name FROM roles " +
            "JOIN user2role ON roles.id = role_id " +
            "JOIN users ON user_login = login " +
            "WHERE login = ?1", nativeQuery = true)
    List<Role> findRolesByUserLogin(String login);
}
