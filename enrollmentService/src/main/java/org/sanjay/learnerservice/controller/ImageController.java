package org.sanjay.learnerservice.controller;

import org.sanjay.learnerservice.dto.ImageModel;
import org.sanjay.learnerservice.repository.ImageRepository;
import org.sanjay.learnerservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(ImageModel imageModel){
        try {
            return imageService.uploadImage(imageModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
