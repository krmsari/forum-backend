package com.example.erendyol.request.Posts;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private String title;
    private String text;
    private Long likeCount;
    private Boolean likedCurrentUser;
}
