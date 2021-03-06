package com.jm.spring_security.service;

import com.jm.spring_security.dao.UserDao;
import com.jm.spring_security.model.Role;
import com.jm.spring_security.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User saveUser(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(2L, "ROLE_USER"));
        user.setRoles(roles);
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
