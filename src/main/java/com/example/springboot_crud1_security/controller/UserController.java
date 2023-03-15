package com.example.springboot_crud1_security.controller;

import com.example.springboot_crud1_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUserById(Model model, Principal principal) {
        model.addAttribute("user", userService.getByEmail(principal.getName()));
        return "infoAboutUser";
    }

}