package com.example.forumApp.services;

import com.example.forumApp.entities.Image;
import com.example.forumApp.entities.Post;
import com.example.forumApp.entities.User;
import com.example.forumApp.repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserService userService;
    private final PostService postService;

    public ImageService(ImageRepository imageRepository, UserService userService, PostService postService) {
        this.imageRepository = imageRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Image getImageByUserId(Long userId) {
        return imageRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Image not found by user id"));
    }

    public Image getImageByPostId(Long postId) {
        return imageRepository.findByPostId(postId).orElseThrow(() -> new RuntimeException("Image not found by post id"));
    }

    public Image saveImage(MultipartFile file) {
        if (imageRepository.existsByFileName(file.getOriginalFilename())) {
            log.info("Image {} have already existed", file.getOriginalFilename());
            return null;
        }
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setMimeType(file.getContentType());
        try {
            image.setData(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageRepository.save(image);
    }

    public Image saveProfileImageToUser(MultipartFile file, Long userId) {
        Image image = saveImage(file);
        if (image != null) {
            User user = userService.getById(userId);
            image.setUser(user);
            return imageRepository.save(image);
        }
        return null;
    }

    public Image savePostImageToPost(MultipartFile file, Long postId) {
        Image image = saveImage(file);
        if (image != null) {
            Post post = postService.getById(postId);
            image.setPost(post);
            return imageRepository.save(image);
        }
        return null;
    }

    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
