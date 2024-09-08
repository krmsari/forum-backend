package com.example.erendyol.request.Comments;

import lombok.Data;

@Data
public class UpdateCommentRequest {

    private String text;
    private Long userId;
    private Long postId;
}
