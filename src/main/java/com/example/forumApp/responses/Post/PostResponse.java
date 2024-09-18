package com.example.forumApp.responses.Post;

import com.example.forumApp.entities.Post;
import lombok.Data;

@Data
public class PostResponse {

    private Long id;
    private String title;
    private String text;
    private Long userId;
    private String username;
    private Long likeCount;
    private Boolean isLikedCurrentUser;
    private byte[] image;
    public PostResponse(Post post,Boolean isLikedCurrentUser,byte[] image) {
        id = post.getId();
        title = post.getTitle();
        text = post.getText();
        userId = post.getUser().getId();
        username = post.getUser().getUsername();
        likeCount = post.getLikeCount();
        this.isLikedCurrentUser = isLikedCurrentUser;
        this.image = image;
    }
}
