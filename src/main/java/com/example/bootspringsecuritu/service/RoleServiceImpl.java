package com.example.bootspringsecuritu.service;

import com.example.bootspringsecuritu.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bootspringsecuritu.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public List<Role> listRoles() {
        Iterable<Role> roles = roleRepository.findAll();
        return (List<Role>) roles;
    }


}
