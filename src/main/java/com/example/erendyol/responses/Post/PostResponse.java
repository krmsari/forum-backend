package com.example.erendyol.responses.Post;

import com.example.erendyol.entities.Like;
import com.example.erendyol.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private Long id;
    private String title;
    private String text;
    private Long userId;
    private String username;
    private Long likeCount;
    private Boolean isLikedCurrentUser;
    public PostResponse(Post post,Boolean isLikedCurrentUser) {
        id = post.getId();
        title = post.getTitle();
        text = post.getText();
        userId = post.getUser().getId();
        username = post.getUser().getUsername();
        likeCount = post.getLikeCount();
        this.isLikedCurrentUser = isLikedCurrentUser;
    }
}
