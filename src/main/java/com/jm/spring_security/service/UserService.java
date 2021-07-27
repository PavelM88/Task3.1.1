package com.jm.spring_security.service;

import com.jm.spring_security.dao.UserDao;
import com.jm.spring_security.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public List<User> allUsers() {
        return userDao.findAll();
    }

    public User getUserById(Integer id) {
        return userDao.getById(id);
    }
}
