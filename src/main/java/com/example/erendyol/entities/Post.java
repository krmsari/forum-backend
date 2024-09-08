package com.example.erendyol.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100,unique = true)
    private String title;
    @Column(nullable = false, length = 1000)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER) //=> many posts can be created by one user
    @JoinColumn(name = "user_id",nullable = false) //=> foreign key
    @OnDelete(action = OnDeleteAction.CASCADE) //=> if user is deleted, all posts of the user will be deleted
    private User user;
}
