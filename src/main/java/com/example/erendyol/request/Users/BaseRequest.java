package com.example.erendyol.request.Users;

import lombok.Data;

@Data
public abstract class BaseRequest {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
}
