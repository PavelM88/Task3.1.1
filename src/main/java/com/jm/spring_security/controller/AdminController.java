package com.jm.spring_security.controller;

import com.jm.spring_security.model.Role;
import com.jm.spring_security.model.User;
import com.jm.spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "rest";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/admin/add")
    public String create(@ModelAttribute("user") User user,
                         @RequestParam("listRole") String listRole) {
        Set<Role> roles = new HashSet<>();
        Role roleUser = new Role();
        if (listRole.equals("ADMIN")) {
            roleUser.setId(1L);
            roleUser.setName("ROLE_ADMIN");
        } else {
            roleUser.setId(2L);
            roleUser.setName("ROLE_USER");
        }
        roles.add(roleUser);
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}
