package com.sanjay.LearnerService.client;

import com.sanjay.LearnerService.dto.ContentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "contentService", url = "${contentService.url}")
public interface ContentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/content/course/{courseId}")
    List<ContentResponse> getCourseContent(@PathVariable("courseId") String courseId);

    @RequestMapping(method = RequestMethod.GET, value = "/api/content/{contentId}")
    ContentResponse getContentById(@PathVariable String contentId);
}
