package com.example.erendyol.controllers;

import com.example.erendyol.entities.Like;
import com.example.erendyol.request.Likes.CreateLikeRequest;
import com.example.erendyol.request.Likes.UpdateLikeRequest;
import com.example.erendyol.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId) {
        return likeService.getById(likeId);
    }

    @GetMapping
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @PostMapping("/like")
    public void like(@RequestBody CreateLikeRequest createLikeRequest) {
        likeService.liked(createLikeRequest);
    }

    @PutMapping("/unlike/{likeId}")
    public void unlike(@PathVariable Long likeId, @RequestBody UpdateLikeRequest updateLikeRequest) {
        likeService.unliked(likeId, updateLikeRequest);
    }

    @DeleteMapping("/delete/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.delete(likeId);
    }
}
