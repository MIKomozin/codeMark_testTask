package com.example.codemark.repository;

import com.example.codemark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    //получить список пользователей из БД
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllUsers();

    //получить конкретного пользователя по login (идентификатор поиска)
    @Query(value = "SELECT * FROM users WHERE login = ?1", nativeQuery = true)
    User findUserByLogin(String login);
}

