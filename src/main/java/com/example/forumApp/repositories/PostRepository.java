package com.example.forumApp.repositories;


import com.example.forumApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostById(Long id);

    List<Post> findAllByUserId(Long userId);

    //user id'si (userId) verilen kullanıcının post id'si (postId) verilen postu beğenip beğenmediğini kontrol eden Boolean dönen @Query'i yaz ve test et.
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM Like l WHERE l.user.id = :userId AND l.post.id = :postId")
    Boolean isLikedByUser(Long userId, Long postId);

    @Query("SELECT i.data FROM Image i WHERE i.post.id = :postId")
    byte[] findImageByPostId(Long postId);




}
