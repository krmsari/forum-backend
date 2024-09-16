package com.example.erendyol.services;

import com.example.erendyol.entities.Image;
import com.example.erendyol.entities.User;
import com.example.erendyol.repositories.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserService userService;

    public ImageService(ImageRepository imageRepository, UserService userService) {
        this.imageRepository = imageRepository;
        this.userService = userService;
    }

    public Image getImageByUserId(Long userId) {
        return imageRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Image not found by user id"));
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

    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
