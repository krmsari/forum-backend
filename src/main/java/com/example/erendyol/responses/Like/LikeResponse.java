package com.example.erendyol.responses.Like;

import com.example.erendyol.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    private Long id;
    private Boolean isLiked;
    private Long postId;
    private Long userId;

    public LikeResponse(Like like){
        id = like.getId();
        postId = like.getPost().getId();
        userId = like.getUser().getId();
    }
}
