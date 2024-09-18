package com.example.forumApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    @Size(min = 2, max = 1000)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER) //=> many posts can be created by one user
    @JoinColumn(name = "post_id",nullable = false) //=> foreign key
    @OnDelete(action = OnDeleteAction.CASCADE) //=> if user is deleted, all posts of the user will be deleted
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER) //=> many posts can be created by one user
    @JoinColumn(name = "user_id",nullable = false) //=> foreign key
    @OnDelete(action = OnDeleteAction.CASCADE) //=> if user is deleted, all posts of the user will be deleted
    private User user;
}
