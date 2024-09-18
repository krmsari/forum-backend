package com.example.forumApp.services;

import com.example.forumApp.entities.Role;
import com.example.forumApp.entities.User;
import com.example.forumApp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(String name, User user) {
        Role role = new Role();
        role.setUser(user);
        role.setName(name);
        roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> getAllRolesByUserId(Long userId) {
        return roleRepository.findAllByUserId(userId);
    }

    public void deleteRole(String name) {
        Role role = roleRepository.findByName(name);
        roleRepository.delete(role);
    }

    public void updateRole(String name, String newName) {
        Role role = roleRepository.findByName(name);
        role.setName(newName);
        roleRepository.save(role);
    }
}
