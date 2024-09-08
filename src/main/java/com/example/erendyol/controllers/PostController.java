package com.example.erendyol.controllers;

import com.example.erendyol.entities.Post;
import com.example.erendyol.request.Posts.CreatePostRequest;
import com.example.erendyol.request.Posts.UpdatePostRequest;
import com.example.erendyol.responses.Post.PostResponses;
import com.example.erendyol.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public Post getPostByPostId(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @GetMapping
    public List<PostResponses> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public String createPost(@RequestBody CreatePostRequest createPostRequest) {
        postService.create(createPostRequest);
        return "Post created successfully";
    }

    @PutMapping("/update/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
        postService.update(postId, updatePostRequest);
        return "Post updated successfully";
    }

    @DeleteMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return "Post deleted successfully";
    }
}
