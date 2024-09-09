package com.example.erendyol.services;

import com.example.erendyol.entities.Comment;
import com.example.erendyol.entities.Post;
import com.example.erendyol.entities.User;
import com.example.erendyol.repositories.CommentRepository;
import com.example.erendyol.request.Comments.CreateCommentRequest;
import com.example.erendyol.request.Comments.UpdateCommentRequest;
import com.example.erendyol.responses.Comment.CommentResponses;
import com.example.erendyol.responses.Post.PostResponses;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Comment getById(Long commentId) {
        return commentRepository.findCommentById(commentId).orElse(null);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<CommentResponses> getCommentsByPostId(Optional<Long> postId) {
        List<Comment> commentList;
        if (postId.isPresent()) {
            commentList = commentRepository.findCommentsByPostId(postId.get());
        } else {
            commentList = commentRepository.findAll();
        }
        return commentList.stream().map(CommentResponses::new) // convert to response, yani response'a çevir
                .collect(Collectors.toList()); // convert to list, yani listeye çevir
    }

    public void create(CreateCommentRequest createCommentRequest) {

        User user = userService.getById(createCommentRequest.getUserId());
        Post post = postService.getById(createCommentRequest.getPostId());

        if (user != null && post != null) {
            Comment comment = new Comment();
            comment.setText(createCommentRequest.getText());
            comment.setUser(user);
            comment.setPost(post);

            commentRepository.save(comment);
        }
        else {
            throw new IllegalArgumentException("User or post not found");
        }
    }

    public void update(Long commentId, UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentRepository.findCommentById(commentId).orElse(null);

        if (comment != null) {
            comment.setText(updateCommentRequest.getText());
            comment.setPost(postService.getById(updateCommentRequest.getPostId()));
            comment.setUser(userService.getById(updateCommentRequest.getUserId()));
            commentRepository.save(comment);
        }
        else {
            throw new IllegalArgumentException("Comment not found");
        }
    }

    public void delete(Long commentId) {
        Comment comment = commentRepository.findCommentById(commentId).orElse(null);
        if (comment != null) {
            commentRepository.delete(comment);
        }
        else {
            throw new IllegalArgumentException("Comment not found");
        }
    }
}
