package com.example.forumApp.controllers;

import com.example.forumApp.request.Users.CreateUserRequest;
import com.example.forumApp.request.Users.UpdateUserRequest;
import com.example.forumApp.responses.User.UserResponse;
import com.example.forumApp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public Long addUser(@RequestBody CreateUserRequest createUserRequest) {
        UserResponse user = userService.add(createUserRequest);
        return user.getId();
    }

    @PutMapping("/update/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updatedUser) {
        return userService.update(userId, updatedUser);

    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "User deleted";
    }

}
