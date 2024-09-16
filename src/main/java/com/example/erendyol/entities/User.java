package com.example.erendyol.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 28)
    @Size(min = 2, max = 28)
    private String name;
    @Column(nullable = false, length = 28)
    @Size(min = 2, max = 28)
    private String surname;
    @Column(unique = true, nullable = false, length = 100)
    @Size(min = 5, max = 68, message = "Email must be between 5 and 68 characters")
    private String email;
    @Column(unique = true,nullable = false, length = 28)
    @Size(min = 2, max = 28, message = "Username must be between 2 and 28 characters")
    private String username;
    @Column(nullable = false, length = 100)
    @Size(min = 2, max = 70, message = "Password must be between 2 and 70 characters")
    private String password;
    @Lob
    private byte[] profilePicture;
}
