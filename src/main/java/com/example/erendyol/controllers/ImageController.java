package com.example.erendyol.controllers;

import com.example.erendyol.entities.Image;
import com.example.erendyol.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public Image saveImage(@RequestPart MultipartFile file) {
        try {
            return imageService.saveImage(file);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
    @PostMapping("/user")
    public Image saveProfileImageToUser(@RequestPart MultipartFile file, @RequestParam Long id) {
        try {
            return imageService.saveProfileImageToUser(file, id);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @PostMapping("/post")
    public Image savePostImageToPost(@RequestPart MultipartFile file, @RequestParam Long id) {
        try {
            return imageService.savePostImageToPost(file, id);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
    //localhost:8080/images/profile?id=1


    @GetMapping("/profile")
    public ResponseEntity<?>  getImageOfUser(@RequestParam Long id) {
        Image image = imageService.getImageByUserId(id);
        ByteArrayResource body = new ByteArrayResource(image.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getMimeType())
                .body(body);
    }
    //www.localhost:8080/images/post?id=1
    @GetMapping("/post")
    public ResponseEntity<?>  getImageOfPost(@RequestParam Long id) {
        Image image = imageService.getImageByPostId(id);
        ByteArrayResource body = new ByteArrayResource(image.getData());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, image.getMimeType())
                .body(body);
    }
}
