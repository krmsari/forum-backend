package com.example.forumApp.repositories;

import com.example.forumApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);


    @Query("SELECT COUNT(p) FROM Post p WHERE p.user.id = :userId")
    Long findPostCountByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.user.id = :userId")
    Long findCommentCountByUserId(@Param("userId") Long userId);

    @Query("SELECT SUM(p.likeCount) FROM Post p WHERE p.user.id = :userId")
    Long findLikeCountByUserId(@Param("userId") Long userId);

    @Query("SELECT i.data FROM Image i WHERE i.user.id = :userId")
    byte[] findImageByUserId(@Param("userId") Long userId);

}
