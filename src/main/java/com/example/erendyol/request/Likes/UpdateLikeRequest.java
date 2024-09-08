package com.example.erendyol.request.Likes;

import lombok.Data;

@Data
public class UpdateLikeRequest {

    private Boolean isLiked;
    private Long userId;
    private Long postId;
}
