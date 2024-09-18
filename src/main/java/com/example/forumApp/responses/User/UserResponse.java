package com.example.forumApp.responses.User;

import com.example.forumApp.entities.Role;
import com.example.forumApp.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

        private Long id;
        private String name;
        private String surname;
        private String email;
        private String username;
        private Long postCount;
        private Long commentCount;
        private Long likeCount;
        private List<Role> roles;
        private byte[] image;

        public UserResponse(User user, Long postCount, Long commentCount,Long likeCount, byte[] image, List<Role> roles) {
            id = user.getId();
            name = user.getName();
            surname = user.getSurname();
            email = user.getEmail();
            username = user.getUsername();
            this.roles = roles;
            this.postCount = postCount;
            this.commentCount = commentCount;
            this.likeCount = likeCount;
            this.image = image;

        }
}
