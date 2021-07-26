package com.jm.spring_security.controller;

import com.jm.spring_security.model.Role;
import com.jm.spring_security.model.User;
import com.jm.spring_security.service.UserDetailsServiceImpl;
import com.jm.spring_security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class AdminController {

    private final UserService userService;
    private final UserDetailsServiceImpl detailsService;

    public AdminController(UserService userService, UserDetailsServiceImpl detailsService) {
        this.userService = userService;
        this.detailsService = detailsService;
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
    public String allUsers(Model model, Principal principal) {
        model.addAttribute("user", detailsService.loadUserByUsername(principal.getName()));
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
                             @RequestParam("role") String role) {
        Set<Role> roles = new HashSet<>();
        Role newRole = new Role();
        if (role.equals("ADMIN")) {
            newRole.setId(1L);
            newRole.setName("ROLE_ADMIN");
        } else {
            newRole.setId(2L);
            newRole.setName("ROLE_USER");
        }
        roles.add(newRole);
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
