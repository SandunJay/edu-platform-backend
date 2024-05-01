package org.sanjay.learnerservice.service.serviceImpl;

import org.sanjay.learnerservice.dto.ImageModel;
import org.sanjay.learnerservice.entity.Image;
import org.sanjay.learnerservice.repository.ImageRepository;
import org.sanjay.learnerservice.service.CloudinaryService;
import org.sanjay.learnerservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<Map> uploadImage(ImageModel imageModel){
        try{
            if (imageModel.getName().isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            if (imageModel.getFile().isEmpty()){
                return ResponseEntity.badRequest().build();
            }

            Image image = new Image();
            image.setName(imageModel.getName());
            image.setUrl(cloudinaryService.uploadImage(imageModel.getFile(),"folder_1"));
            if (image.getUrl()== null){
                return ResponseEntity.badRequest().build();
            }
            imageRepository.save(image);
            return ResponseEntity.ok().body(Map.of("url", image.getUrl()));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ResponseEntity<Map> uploadMedia(ImageModel imageModel) {
        if (imageModel.getFile().isEmpty() || imageModel.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String fileType = imageModel.getFile().getContentType();
        if (!fileType.startsWith("video/") && !fileType.startsWith("image/")) {
            return ResponseEntity.badRequest().build();
        }

        String folderName = fileType.startsWith("video/") ? "videos" : "images";

        Image image = new Image();
        image.setName(imageModel.getName());
        String mediaUrl = cloudinaryService.uploadImage(imageModel.getFile(), folderName);

        if (mediaUrl == null) {
            return ResponseEntity.badRequest().build();
        }

        image.setUrl(mediaUrl);
        imageRepository.save(image);

        Map<String, String> response = new HashMap<>();
        response.put("url", image.getUrl());

        return ResponseEntity.ok(response);
    }

}
