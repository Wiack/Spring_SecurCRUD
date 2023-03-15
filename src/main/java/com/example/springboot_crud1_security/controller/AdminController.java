package com.example.springboot_crud1_security.controller;

import com.example.springboot_crud1_security.model.User;
import com.example.springboot_crud1_security.service.RoleService;
import com.example.springboot_crud1_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String showAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("listUsers", userService.getAllUsers());
        model.addAttribute("user", userService.getByEmail(principal.getName()));
        return "allUser";
    }


    @GetMapping("/newUser")
    public String getCreationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRole", roleService.findAll());
        return "detailsNewUser";
    }

    @PostMapping()
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/editInfo/{id}")
    public String getEditingForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("listRole", roleService.findAll());
        return "editInfoUser";
    }


    @PostMapping("/updeteInfo")
    public String saveEditInfoUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id, Principal principal) {
        userService.deleteUserById(id, principal);
        return "redirect:/admin";
    }
}
