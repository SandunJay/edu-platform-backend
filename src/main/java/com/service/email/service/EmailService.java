package com.service.email.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendMail( String to, String[] cc, String subject, String body);

    //String sendConformationMail(MultipartFile[] file, String to);

    //String sendMail(MultipartFile[] file, String to);
}
