package com.jm.spring_security.controller;

import com.jm.spring_security.dao.UserDao;
import com.jm.spring_security.model.User;
import com.jm.spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDao userDao;

    public UserController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        User user = userDao.findByUserName(principal.getName());
        model.addAttribute(user);
        return "show";
    }
}
