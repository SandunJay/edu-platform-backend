package org.sanjay.learnerservice.service;

import org.sanjay.learnerservice.dto.ImageModel;
import org.sanjay.learnerservice.entity.Image;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ImageService {

    ResponseEntity<Map> uploadImage(ImageModel imageModel);

    ResponseEntity<Map> uploadMedia(ImageModel imageModel);
}
