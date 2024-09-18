package com.example.forumApp.request.Likes;

import lombok.Data;

@Data
public class UpdateLikeRequest {

    private Long userId;
    private Long postId;

}
