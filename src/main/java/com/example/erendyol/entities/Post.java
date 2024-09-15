package com.example.erendyol.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 100, message = "Post title must be between 3 and 100 characters")
    private String title;
    @Column(nullable = false)
    @Size(min = 3, max = 1000,message = "Post text must be between 3 and 1000 characters")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER) //=> many posts can be created by one user
    @JoinColumn(name = "user_id",nullable = false) //=> foreign key
    @OnDelete(action = OnDeleteAction.CASCADE) //=> if user is deleted, all posts of the user will be deleted
    private User user;

    private Long likeCount;
}
