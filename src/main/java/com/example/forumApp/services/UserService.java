package com.example.forumApp.services;

import com.example.forumApp.entities.User;
import com.example.forumApp.repositories.UserRepository;
import com.example.forumApp.request.Users.BaseRequest;
import com.example.forumApp.request.Users.CreateUserRequest;
import com.example.forumApp.request.Users.UpdateUserRequest;
import com.example.forumApp.responses.User.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User getById(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public Long postOfUser(Long userId){
        return userRepository.findPostCountByUserId(userId);
    }

    public Long commentOfUser(Long userId){
        return userRepository.findCommentCountByUserId(userId);
    }

    public Long likeOfUser(Long userId){
        return userRepository.findLikeCountByUserId(userId);
    }

    public byte[] imageOfUser(Long userId){
        return userRepository.findImageByUserId(userId);
    }

    public UserResponse getUser(Long userId){
        return new UserResponse(getById(userId), postOfUser(userId), commentOfUser(userId), likeOfUser(userId), imageOfUser(userId), roleService.getAllRolesByUserId(userId));
    }

    public List<UserResponse> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponse(user,postOfUser(user.getId()), commentOfUser(user.getId()),likeOfUser(user.getId()),imageOfUser(user.getId()),roleService.getAllRolesByUserId(user.getId()))).toList();
    }

    public UserResponse add(CreateUserRequest createUserRequest){
        User user = userRepository.findUserById(createUserRequest.getId()).orElse(null);
        if (user != null){
            throw new IllegalArgumentException("User already exists");
        }
        user = new User();
        mapUser(user, createUserRequest);
        roleService.createRole("USER", user);
        return new UserResponse(user, postOfUser(user.getId()), commentOfUser(user.getId()), likeOfUser(user.getId()), imageOfUser(user.getId()), roleService.getAllRolesByUserId(user.getId()));

    }

    public UserResponse update(Long userId, UpdateUserRequest updateUserRequest){
        User user = userRepository.findUserById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        mapUser(user, updateUserRequest);
        return new UserResponse(user, postOfUser(userId), commentOfUser(userId), likeOfUser(userId), imageOfUser(userId), roleService.getAllRolesByUserId(userId));

    }

    public void delete(Long userId){
        userRepository.deleteById(userId);
    }

    private void mapUser(User user, BaseRequest createUserRequest){
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setEmail(createUserRequest.getEmail());
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        userRepository.save(user);
    }



}
