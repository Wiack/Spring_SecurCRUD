package com.example.springboot_crud1_security.service;

import com.example.springboot_crud1_security.model.Role;
import com.example.springboot_crud1_security.model.User;
import com.example.springboot_crud1_security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if (userRepository.findByEmail(user.getUsername()) == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteUserById(Long id, Principal principal) {
        if (!principal.getName().equals(getById(id).getUsername())) {
            userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User userForUpdate = getById(user.getId());
        final String USR = "ROLE_USER";
        final String ADM = "ROLE_ADMIN";

        if (user.getRoles().contains(roleService.findByName(ADM))) {
            user.getRoles().add(roleService.findByName(USR));
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(userForUpdate.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }



}
