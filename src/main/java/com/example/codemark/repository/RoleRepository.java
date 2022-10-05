package com.example.codemark.repository;

import com.example.codemark.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role, Integer> {

    //получить конкрутную роль по name (идентификатор поиска)
    @Query(value = "SELECT * FROM roles WHERE name = ?1", nativeQuery = true)
    Role findRoleByName(String name);
}
