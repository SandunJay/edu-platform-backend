package com.sanjay.LearnerService.client;

import com.sanjay.LearnerService.DTO.ContentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "contentService", url = "${contentService.url}")
public interface ContentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/course/by-courseId/{courseId}")
    List<ContentResponse> getCourseContent(@PathVariable("courseId") String courseId);

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/content/{contentId}")
    ContentResponse getContentById(@PathVariable String contentId);
}
