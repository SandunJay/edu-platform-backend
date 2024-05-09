package com.lms.microservices.contentsservice.controller;

import com.lms.microservices.contentsservice.dto.ContentRequest;
import com.lms.microservices.contentsservice.dto.ContentResponse;
import com.lms.microservices.contentsservice.model.Content;
import com.lms.microservices.contentsservice.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createContent(@RequestBody ContentRequest contentRequest) {
        contentService.createContent(contentRequest);
        return "Content created successfully!";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContentResponse updateContent(@PathVariable String id, @RequestBody ContentRequest contentRequest) {
        return contentService.updateContent(id, contentRequest);
    }

    @GetMapping("/course/{courseId}")
    public List<ContentResponse> getContentsByCourseId(@PathVariable("courseId") String courseId) {
        return contentService.getContentsByCourseId(courseId);
    }

    @GetMapping("/{contentId}")
    public ContentResponse getContentById(@PathVariable String contentId) {
        return contentService.getContentById(contentId);
    }
}
