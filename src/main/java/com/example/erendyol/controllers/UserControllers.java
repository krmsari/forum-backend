package com.example.erendyol.controllers;

import com.example.erendyol.entities.User;
import com.example.erendyol.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getById(userId);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public String addUser(@RequestBody User newUser) {
        userService.add(newUser);
        return "User added";
    }

    @PutMapping("/update/{userId}")
    public String updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        userService.update(userId, updatedUser);
        return "User updated";
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "User deleted";
    }

}
