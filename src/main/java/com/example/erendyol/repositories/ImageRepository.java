package com.example.erendyol.repositories;

import com.example.erendyol.entities.Image;
import com.example.erendyol.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByFileName(String fileName);

    @Query("SELECT i FROM Image i WHERE i.user.id = :userId")
    Optional<Image> findByUserId(Long userId);

    boolean existsByFileName(String fileName);
}
