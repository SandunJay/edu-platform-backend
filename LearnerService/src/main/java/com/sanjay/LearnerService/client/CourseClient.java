package com.sanjay.LearnerService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "courseService", url = "${courseService.url}")
public interface CourseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/courses")
    Object getCourseDetails(@RequestParam("courseId") String courseId);
}
