package com.sanjay.LearnerService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "contentService", url = "${contentService.url}")
public interface ContentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/content")
    ResponseEntity getCourseContent(List<String> courseId);
}
