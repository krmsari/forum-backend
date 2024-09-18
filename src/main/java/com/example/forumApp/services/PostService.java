package com.example.forumApp.services;

import com.example.forumApp.entities.Post;
import com.example.forumApp.entities.User;
import com.example.forumApp.repositories.PostRepository;
import com.example.forumApp.request.Posts.CreatePostRequest;
import com.example.forumApp.request.Posts.UpdatePostRequest;
import com.example.forumApp.responses.Post.PostResponse;
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

    public void increaseLikeCount(Post post) {
        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);
    }

    public void decreaseLikeCount(Post post) {
        post.setLikeCount(post.getLikeCount() - 1);
        postRepository.save(post);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public byte[] imageOfPost(Long postId) {
        return postRepository.findImageByPostId(postId);
    }

    public Post getById(Long postId) {
        return postRepository.findPostById(postId).orElseThrow( () -> new RuntimeException("Post not found"));
    }

    public List<PostResponse> getAllPosts() {
        List<Post> postList;
        postList = postRepository.findAll();
        return postList.stream().map(post -> new PostResponse(post,postRepository.isLikedByUser(post.getUser().getId(),post.getId()),imageOfPost(post.getId()))).collect(Collectors.toList());
    }


    public PostResponse create(CreatePostRequest createPostRequest) {
        User user = userService.getById(createPostRequest.getUserId());
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setText(createPostRequest.getText());
        newPost.setLikeCount(0L);

        newPost.setUser(user);
        return new PostResponse(postRepository.save(newPost),false,imageOfPost(newPost.getId()));
    }

    public PostResponse update(Long postId, UpdatePostRequest updatePostRequest) {
        Optional<Post> getPost = postRepository.findPostById(postId);
        Post post = getPost.orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(updatePostRequest.getTitle());
        post.setText(updatePostRequest.getText());
        post.setLikeCount(updatePostRequest.getLikeCount());
        postRepository.save(post);
        return new PostResponse(post,postRepository.isLikedByUser(post.getUser().getId(),postId),imageOfPost(postId));
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
