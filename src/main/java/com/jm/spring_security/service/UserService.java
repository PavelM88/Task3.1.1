package com.jm.spring_security.service;

import com.jm.spring_security.dao.UserDao;
import com.jm.spring_security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    public User updateUser(User user) {
        if (user.getPassword().length() == 0) {
            user.setPassword(userDao.getById(user.getId()).getPassword());
        }
        if (passwordEncoder.matches(user.getPassword(), userDao.getById(user.getId()).getPassword())) {
            user.setPassword(userDao.getById(user.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public List<User> allUsers() {
        return userDao.findAll();
    }

    public User getUserById(Integer id) {
        return userDao.findById(id).get();
    }
}
