package com.example.springboot_crud1_security.init;

import com.example.springboot_crud1_security.model.Role;
import com.example.springboot_crud1_security.model.User;
import com.example.springboot_crud1_security.service.RoleService;
import com.example.springboot_crud1_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Init implements CommandLineRunner {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public Init(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (roleService.findByName("ROLE_ADMIN") == null) {
            roleService.saveRole(new Role("ROLE_ADMIN"));
        }

        if (roleService.findByName("ROLE_USER") == null) {
            roleService.saveRole(new Role("ROLE_USER"));
        }


        User admin = userService.getByEmail("admin@gmail.com");
        if (admin == null) {
            admin = new User(
                    "adminName",
                    "adminLastName",
                    33,
                    "admin@gmail.com",
                    "0000",
                    Collections.singleton(roleService.findByName("ROLE_ADMIN")));

            userService.addUser(admin);
        }

        User user = userService.getByEmail("user@gmail.com");
        if (user == null) {
            user = new User(
                    "userName",
                    "userLastName",
                    23,
                    "user@gmail.com",
                    "0000",
                    Collections.singleton(roleService.findByName("ROLE_USER")));

            userService.addUser(user);
        }
    }
}



