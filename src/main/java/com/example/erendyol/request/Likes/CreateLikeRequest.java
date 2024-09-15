package com.example.erendyol.request.Likes;

import lombok.Data;

@Data
public class CreateLikeRequest {

    private Long id;
    private Long userId;
    private Long postId;
}
