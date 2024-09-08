package com.example.erendyol.responses.Post;

import com.example.erendyol.entities.Post;
import lombok.Data;

@Data
public class PostResponses {

    private Long id;
    private String title;
    private String text;
    private Long userId;
    private String username;

    public PostResponses(Post post){
        id = post.getId();
        title = post.getTitle();
        text = post.getText();
        userId = post.getUser().getId();
        username = post.getUser().getUsername();
    }
}
