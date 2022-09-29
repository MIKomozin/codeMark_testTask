package com.example.codemark.repository;

import com.example.codemark.entity.User2Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface User2RoleRepository extends JpaRepository<User2Role, Integer> {

    //получить конкретного пользователя по login (идентификатор поиска)
    @Query(value = "SELECT * FROM user2role WHERE user_login = ?1", nativeQuery = true)
    List<User2Role> findUser2RoleByLogin(String login);

}
