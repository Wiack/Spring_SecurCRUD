package com.example.springboot_crud1_security.service;

import com.example.springboot_crud1_security.model.Role;
import com.example.springboot_crud1_security.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public boolean containsName(final String nameRole){
        return findAll().stream().map(Role::getName).filter(nameRole::equals).findFirst().isPresent();
    }

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
