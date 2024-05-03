package org.sanjay.learnerservice.service.serviceImpl;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.sanjay.learnerservice.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Resource
    private Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file, String folderName) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
