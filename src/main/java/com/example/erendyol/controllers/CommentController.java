package com.example.erendyol.controllers;

import com.example.erendyol.entities.Comment;
import com.example.erendyol.request.Comments.CreateCommentRequest;
import com.example.erendyol.request.Comments.UpdateCommentRequest;
import com.example.erendyol.responses.Comment.CommentResponse;
import com.example.erendyol.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getById(commentId);
    }
    @GetMapping
    public List<CommentResponse> getCommentsByPostId(@RequestParam Optional<Long> postId) {
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
    @PostMapping
    public CommentResponse createComment(@RequestBody CreateCommentRequest createCommentRequest){
        return commentService.create(createCommentRequest);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest){

        return commentService.update(commentId, updateCommentRequest);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.delete(commentId);
        return "Comment deleted successfully";
    }
}
