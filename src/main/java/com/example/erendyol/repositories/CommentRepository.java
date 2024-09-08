package com.example.erendyol.repositories;

import com.example.erendyol.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    List<Comment> findCommentsByPostId(Long postId);
}
