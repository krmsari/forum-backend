package com.example.erendyol.responses.User;

import com.example.erendyol.entities.User;
import lombok.Data;

@Data
public class UserResponse {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private String username;
        private String password;
        private Long postCount;
        private Long commentCount;
        private Long likeCount;
        private byte[] image;

        public UserResponse(User user, Long postCount, Long commentCount,Long likeCount, byte[] image) {
            id = user.getId();
            name = user.getName();
            surname = user.getSurname();
            email = user.getEmail();
            username = user.getUsername();
            password = user.getPassword();
            this.postCount = postCount;
            this.commentCount = commentCount;
            this.likeCount = likeCount;
            this.image = image;
        }
}
