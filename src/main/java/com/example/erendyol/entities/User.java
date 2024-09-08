package com.example.erendyol.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 28)
    private String name;
    @Column(nullable = false, length = 28)
    private String surname;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(unique = true,nullable = false, length = 28)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
}
