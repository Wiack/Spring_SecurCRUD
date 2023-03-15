package com.example.springboot_crud1_security.controller;

import com.example.springboot_crud1_security.service.RoleService;
import com.example.springboot_crud1_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public LoginController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


}
