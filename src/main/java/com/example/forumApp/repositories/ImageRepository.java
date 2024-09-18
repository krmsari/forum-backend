package com.example.forumApp.repositories;

import com.example.forumApp.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByFileName(String fileName);

    @Query("SELECT i FROM Image i WHERE i.user.id = :userId")
    Optional<Image> findByUserId(Long userId);

    Optional<Image> findByPostId(Long postId);
    boolean existsByFileName(String fileName);
}
