package com.example.forumApp.repositories;

import com.example.forumApp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    List<Role> findAllByUserId(Long userId);
}
