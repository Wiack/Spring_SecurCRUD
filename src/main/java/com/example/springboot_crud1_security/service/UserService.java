package com.example.springboot_crud1_security.service;

import com.example.springboot_crud1_security.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void addUser(User user);

    void deleteUserById(Long id, Principal principal);

    void updateUser(User user);

    User getById(Long id);

    User getByEmail(String email);

}
