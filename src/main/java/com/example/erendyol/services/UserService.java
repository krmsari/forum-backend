package com.example.erendyol.services;

import com.example.erendyol.entities.User;
import com.example.erendyol.repositories.UserRepository;
import com.example.erendyol.request.Users.CreateUserRequest;
import com.example.erendyol.responses.User.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public UserResponse add(CreateUserRequest createUserRequest){
        User user = userRepository.findUserById(createUserRequest.getId()).orElse(null);
        if (user != null){
            throw new IllegalArgumentException("User already exists");
        }
        User newUser = new User();
        newUser.setId(createUserRequest.getId());
        newUser.setName(createUserRequest.getName());
        newUser.setSurname(createUserRequest.getSurname());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setPassword(createUserRequest.getPassword());

        return new UserResponse(userRepository.save(newUser));
    }

    public User update(Long userId, User updatedUser){
        Optional<User> user  = userRepository.findUserById(userId);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(updatedUser.getUsername());
            foundUser.setPassword(updatedUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }else {
            return null;
        }
    }

    public void delete(Long userId){
        userRepository.deleteById(userId);
    }

}
