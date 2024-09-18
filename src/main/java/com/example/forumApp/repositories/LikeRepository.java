package com.example.forumApp.repositories;

import com.example.forumApp.entities.Like;
import com.example.forumApp.entities.Post;
import com.example.forumApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByUserAndPost(User user, Post post);
    void deleteByUserAndPost(User user, Post post);

}
