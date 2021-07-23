package com.jm.spring_security.dao;

import com.jm.spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
