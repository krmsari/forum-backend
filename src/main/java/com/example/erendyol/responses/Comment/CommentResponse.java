package com.example.erendyol.responses.Comment;

import com.example.erendyol.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    private Long id;
    private String text;
    private Long postId;
    private Long userId;
    private String name;
    private String surname;
    private String username;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.name = comment.getUser().getName();
        this.surname = comment.getUser().getSurname();
        this.username = comment.getUser().getUsername();
    }
}
