package com.example.erendyol.repositories;

import com.example.erendyol.entities.Like;
import com.example.erendyol.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> getLikeById (Long likeId);
}
