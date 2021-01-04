package com.example.bootspringsecuritu.service;

import com.example.bootspringsecuritu.model.Role;

import java.util.List;

public interface RoleService {
    public Role findRoleById(Long id);
    List<Role> listRoles();
}

