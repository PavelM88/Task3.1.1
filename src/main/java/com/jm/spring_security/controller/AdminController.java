package com.jm.spring_security.controller;

import com.jm.spring_security.model.Role;
import com.jm.spring_security.model.User;
import com.jm.spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
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

    @GetMapping("/admin")
    public String allUsers(Model model) {
        List<User> users = userService.allUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/admin/add")
    public String create(@ModelAttribute("user") User user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/add";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
            return "/add";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUserId(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/admin/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(required = false, name = "ROLE_ADMIN") String roleAdmin,
                             @RequestParam(required = false, name = "ROLE_USER") String roleUser) {
        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(new Role(1L, roleAdmin));
        }
        if (roleUser != null) {
            roles.add(new Role(2L, roleUser));
        }
        user.setUserName(user.getUsername());
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin";
    }
}
