package com.example.erendyol.request.Users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateUserRequest extends BaseRequest {

    private Long id;

}
