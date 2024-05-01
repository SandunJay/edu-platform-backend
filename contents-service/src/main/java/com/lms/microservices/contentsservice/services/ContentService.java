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
            contentRepository.save(content);
        }else{
            throw new RuntimeException("Course not found with id " + contentRequest.courseId());
        }

}

    public ContentResponse updateContent(Long id, ContentRequest contentRequest) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id " + id));
        content.setTitle(contentRequest.title());
        content.setDescription(contentRequest.description());
        content.setCourseId(contentRequest.courseId());
        contentRepository.save(content);
        log.info("Content updated successfully");
        return new ContentResponse(content.getId(), content.getTitle(), content.getDescription(), content.getCourseId(), content.getCreatedDate(), content.getLastUpdatedDate());
    }

    public List<Content> getContentsByCourseId(String courseId) {
        return contentRepository.findByCourseId(courseId);
    }


}
