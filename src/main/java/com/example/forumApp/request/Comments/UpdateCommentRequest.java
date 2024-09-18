package com.example.forumApp.request.Comments;

import lombok.Data;

@Data
public class UpdateCommentRequest {

    private String text;
    private Long userId;
    private Long postId;
}
