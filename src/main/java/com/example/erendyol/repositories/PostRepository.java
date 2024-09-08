package com.example.erendyol.repositories;


import com.example.erendyol.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostById(Long id);

    List<Post> findAllByUserId(Long userId);

}
