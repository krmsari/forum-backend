package com.example.erendyol.controllers;

import com.example.erendyol.entities.Post;
import com.example.erendyol.request.Posts.CreatePostRequest;
import com.example.erendyol.request.Posts.UpdatePostRequest;
import com.example.erendyol.responses.Post.PostResponse;
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
    public List<PostResponse> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Long createPost(@RequestBody CreatePostRequest createPostRequest) {
        PostResponse postResponse = postService.create(createPostRequest);
        return postResponse.getId();
    }

    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest updatePostRequest) {
        return postService.update(postId, updatePostRequest);
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return "Post deleted successfully";
    }
}
