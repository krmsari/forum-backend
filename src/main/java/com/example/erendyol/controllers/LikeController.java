package com.example.erendyol.controllers;

import com.example.erendyol.services.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{userId}/{postId}")
    public void like(@PathVariable  Long userId, @PathVariable Long postId) {
        likeService.like(userId, postId);
    }

    @DeleteMapping("/{userId}/{postId}")
    public void unlike(@PathVariable Long userId, @PathVariable Long postId) {
        likeService.unlike(userId, postId);
    }
}
