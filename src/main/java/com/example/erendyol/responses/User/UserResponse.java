package com.example.erendyol.responses.User;

import com.example.erendyol.entities.User;
import lombok.Data;

@Data
public class UserResponse {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private String username;
        private String password;

        public UserResponse(User user){
            id = user.getId();
            name = user.getName();
            surname = user.getSurname();
            email = user.getEmail();
            username = user.getUsername();
            password = user.getPassword();
        }
}
