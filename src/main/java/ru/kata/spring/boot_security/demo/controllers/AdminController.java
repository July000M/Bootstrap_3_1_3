package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(@AuthenticationPrincipal User user,
                              @ModelAttribute("role") Role role,

                              Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("allUsers", userList);
        model.addAttribute("listRoles", userService.getListRoles());
        model.addAttribute("user", user);
        return "main_page_admin";
    }

    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") User user,
                          @ModelAttribute("role") Role role,
                          Model model) {
        model.addAttribute("listRoles", userService.getListRoles());
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           Model model) {
        model.addAttribute("listRoles", userService.getListRoles());
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
