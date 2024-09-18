package com.example.forumApp.request.Users;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserRequest extends BaseRequest {

    private Long id;

}
