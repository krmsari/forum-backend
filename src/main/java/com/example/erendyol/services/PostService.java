package com.example.erendyol.services;

import com.example.erendyol.entities.Post;
import com.example.erendyol.entities.User;
import com.example.erendyol.repositories.PostRepository;
import com.example.erendyol.request.Posts.CreatePostRequest;
import com.example.erendyol.request.Posts.UpdatePostRequest;
import com.example.erendyol.responses.Post.PostResponses;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post getById(Long postId) {
        return postRepository.findPostById(postId).orElse(null);

    }

    public List<PostResponses> getAllPosts(Optional<Long> userId) {
        List<Post> postList;
        if (userId.isPresent()) {
            postList = postRepository.findAllByUserId(userId.get());
        } else {
            postList = postRepository.findAll();
        }
        return postList.stream().map(PostResponses::new).collect(Collectors.toList());
    }

    public void create(CreatePostRequest createPostRequest) {
        User user = userService.getById(createPostRequest.getUserId());
        if (user == null) {
            return ;
        }
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setText(createPostRequest.getText());
        newPost.setUser(user);
        postRepository.save(newPost);
    }

    public void update(Long postId, UpdatePostRequest updatePostRequest) {
        Optional<Post> getPost = postRepository.findPostById(postId);
        if (getPost.isPresent()) {
            Post post = getPost.get();
            post.setTitle(updatePostRequest.getTitle());
            post.setText(updatePostRequest.getText());
            postRepository.save(post);
        }
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
