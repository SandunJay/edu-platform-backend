package com.lms.microservices.contentsservice.controller;

import com.lms.microservices.contentsservice.dto.ContentRequest;
import com.lms.microservices.contentsservice.dto.ContentResponse;
import com.lms.microservices.contentsservice.model.Content;
import com.lms.microservices.contentsservice.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000")
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

    @GetMapping("/{courseId}")
    public List<Content> getContentsByCourseId(@PathVariable String courseId) {
        return contentService.getContentsByCourseId(courseId);
    }

    @GetMapping("/getcontent/{id}")
    ContentResponse getContentById(@PathVariable String id) {
        return contentService.getContentById(id);
    }


    @PostMapping("/approve/{id}")
    public void approveContent(@PathVariable String id) {
        contentService.approveContent(id);
    }

    @GetMapping("/unapproved")
    public List<Content> getUnapprovedContent(){
        return contentService.getUnapprovedContent();
    }
}
