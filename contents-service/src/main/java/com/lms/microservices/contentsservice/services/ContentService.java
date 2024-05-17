package com.lms.microservices.contentsservice.services;


import com.lms.microservices.contentsservice.client.CourseClient;
import com.lms.microservices.contentsservice.dto.ContentRequest;
import com.lms.microservices.contentsservice.dto.ContentResponse;
import com.lms.microservices.contentsservice.exception.ResourceNotFoundException;
import com.lms.microservices.contentsservice.model.Content;
import com.lms.microservices.contentsservice.repositoty.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ContentService {


    private final ContentRepository contentRepository;
    private final CourseClient courseClient;

     public String generateUniqueId() {
    String id;
    do {
        id = "C" + (int)(Math.random() * 10000);
    } while(contentRepository.existsById(id));
    return id;
}

    public void createContent(ContentRequest contentRequest) {

        var isCourseExist = courseClient.existsCourseById(contentRequest.courseId());
        if (isCourseExist) {
            Content content = new Content();
            content.setId(generateUniqueId());
            content.setTitle(contentRequest.title());
            content.setDescription(contentRequest.description());
            content.setCourseId(contentRequest.courseId());
            content.setVideoUrl(contentRequest.videoUrl());
            content.setPdfUrl(contentRequest.pdfUrl());
            content.setStatus(false);
            contentRepository.save(content);
        }else{
            throw new RuntimeException("Course not found with id " + contentRequest.courseId());
        }
}

    public ContentResponse updateContent(String id, ContentRequest contentRequest) {
    List<Content> contents = contentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Content not found with id " + id));


        if (contents.isEmpty()) {
            throw new ResourceNotFoundException("Content not found with id " + id);
        }

        Content content = contents.get(0);

        if (contentRequest.title() != null) {
        content.setTitle(contentRequest.title());
    }
    if (contentRequest.description() != null) {
        content.setDescription(contentRequest.description());
    }
    if (contentRequest.courseId() != null) {
        content.setCourseId(contentRequest.courseId());
    }
    if (contentRequest.videoUrl() != null) {
        content.setVideoUrl(contentRequest.videoUrl());
    }
    if (contentRequest.pdfUrl() != null) {
        content.setPdfUrl(contentRequest.pdfUrl());
    }

    contentRepository.save(content);
    log.info("Content updated successfully");

    return new ContentResponse(content.getId(), content.getTitle(), content.getDescription(), content.getCourseId(),content.getVideoUrl(),content.getPdfUrl(), content.isStatus(),content.getCreatedDate(), content.getLastUpdatedDate());
}

     public List<Content> getContentsByCourseId(String courseId) {
    return contentRepository.findByCourseId(courseId)
                            .stream()
                            .filter(Content::isStatus)
                            .collect(Collectors.toList());
     }

    public ContentResponse getContentById(String id) {
        List<Content> contents = contentRepository.findById(id);
        if (contents.isEmpty()) {
            throw new ResourceNotFoundException("Content not found for course id " + id);
        }
        Content content = contents.get(0);
        return new ContentResponse(content.getId(), content.getTitle(), content.getDescription(), content.getCourseId(),content.getVideoUrl(),content.getPdfUrl(), content.isStatus(),content.getCreatedDate(), content.getLastUpdatedDate());
    }


//    public List<Content> getContentsByCoursehttps://github.com/SandunJay-s/edu-platform-backend/pull/44/conflict?name=contents-service%252Fsrc%252Fmain%252Fjava%252Fcom%252Flms%252Fmicroservices%252Fcontentsservice%252Fservices%252FContentService.java&ancestor_oid=94192814bd28b0bc9dde25ce987000f188762920&base_oid=6a3f20f40691be33a79e9f8a8e8f07436e03643f&head_oid=c4e3dc51af4f427b48dc9be1ad87149bdd953b49Id(String courseId) {
//        return contentRepository.findByCourseId(courseId);
//    }

    public List<ContentResponse> getContentsByCourseId(String courseId) {
        List<Content> contents = contentRepository.findByCourseId(courseId);
        log.info("Retrieved contents for courseId {}: {}", courseId, contents);

        return contents.stream()
                .map(content -> new ContentResponse(
                        content.getId(),
                        content.getTitle(),
                        content.getDescription(),
                        content.getCourseId(),
                        content.getVideoUrl(),
                        content.getPdfUrl(),
                        content.getCreatedDate(),
                        content.getLastUpdatedDate()
                ))
                .collect(Collectors.toList());
    }


    public ContentResponse getContentById(String contentId)
    {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id " + contentId));
        log.info("Content found successfully");
        return new ContentResponse(content.getId(), content.getTitle(), content.getDescription(), content.getCourseId(),content.getVideoUrl(), content.getPdfUrl()  ,content.getCreatedDate(), content.getLastUpdatedDate());

    public List<Content> getUnapprovedContent() {
        List<Content> allContents = contentRepository.findAll();
        return allContents.stream()
                .filter(content -> !content.isStatus()) // only include content that is not approved
                .collect(Collectors.toList());
    }

    public void approveContent(String id) {
        List<Content> contents = contentRepository.findById(id);
        if (contents.isEmpty()) {
            throw new ResourceNotFoundException("Content not found for course id " + id);
        }
        for (Content content : contents) {
            content.setStatus(true);
            contentRepository.save(content);
        }
    }


}






