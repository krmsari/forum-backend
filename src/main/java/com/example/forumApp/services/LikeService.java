package com.example.forumApp.services;

import com.example.forumApp.entities.Like;
import com.example.forumApp.entities.Post;
import com.example.forumApp.entities.User;
import com.example.forumApp.repositories.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public Like getLikeByUserAndPost(Long userId, Long postId) {
        User user = userService.getById(userId);
        Post post = postService.getById(postId);
        return likeRepository.findByUserAndPost(user, post ).orElseThrow(() -> new RuntimeException("Like not found"));
    }

    public void like(Long userId, Long postId) {
        User user = userService.getById(userId);
        Post post = postService.getById(postId);
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
        postService.increaseLikeCount(post);
    }

    public void unlike(Long userId, Long postId) {
        User user = userService.getById(userId);
        Post post = postService.getById(postId);
        Like like = likeRepository.findByUserAndPost(user, post).orElseThrow(() -> new RuntimeException("Like not found"));
        likeRepository.delete(like);
        postService.decreaseLikeCount(post);
    }
}
