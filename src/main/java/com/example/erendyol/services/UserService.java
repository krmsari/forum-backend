package com.example.erendyol.services;

import com.example.erendyol.entities.User;
import com.example.erendyol.repositories.UserRepository;
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
        return userRepository.findUserById(id).get();
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User add(User newUser){
        return userRepository.save(newUser);
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
