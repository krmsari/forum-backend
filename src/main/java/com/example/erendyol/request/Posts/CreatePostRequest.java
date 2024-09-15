package com.example.erendyol.request.Posts;

import lombok.Data;

@Data
public class CreatePostRequest {
    private Long id;
    private String title;
    private Long likeCount;
    private Boolean likedCurrentUser;
    private String text;
    private Long userId;
}
