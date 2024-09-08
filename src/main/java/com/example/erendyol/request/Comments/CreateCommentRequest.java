package com.example.erendyol.request.Comments;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private Long id;
    private String text;
    private Long userId;
    private Long postId;
}
