package com.example.erendyol.services;

import com.example.erendyol.entities.Like;
import com.example.erendyol.entities.Post;
import com.example.erendyol.entities.User;
import com.example.erendyol.repositories.LikeRepository;
import com.example.erendyol.request.Likes.CreateLikeRequest;
import com.example.erendyol.request.Likes.UpdateLikeRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Like getById(Long likeId){
        return likeRepository.getLikeById(likeId).orElse(null);
    }

    public List<Like> getAllLikes(){
        return likeRepository.findAll();
    }

    public void liked(CreateLikeRequest createLikeRequest){
        User user = userService.getById(createLikeRequest.getId());
        Post post = postService.getById(createLikeRequest.getPostId());

        if(user != null && post != null){
            Like like = new Like();
            like.setId(createLikeRequest.getId());
            like.setIsLiked(createLikeRequest.getIsLiked());
            like.setUser(user);
            like.setPost(post);

            likeRepository.save(like);
        }else {
            throw new IllegalArgumentException("User or post not found.");
        }
    }

    public void unliked(Long likeId, UpdateLikeRequest updateLikeRequest){
        Like like = likeRepository.getLikeById(likeId).orElse(null);

        if(like != null){
            like.setIsLiked(updateLikeRequest.getIsLiked());

            likeRepository.save(like);
        }else {
            throw new IllegalArgumentException("Like not found.");
        }
    }

    public void delete(Long likeId){
        likeRepository.deleteById(likeId);
    }
}
