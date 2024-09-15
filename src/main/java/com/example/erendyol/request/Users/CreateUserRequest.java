package com.example.erendyol.request.Users;

import lombok.Data;

@Data
public class CreateUserRequest {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private String username;
        private String password;
}
