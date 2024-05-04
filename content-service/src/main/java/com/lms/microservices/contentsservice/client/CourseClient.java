package com.lms.microservices.contentsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "course-service", url = "http://localhost:8082")
public interface CourseClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/course/{courseId}/exists")
    boolean existsCourseById(@RequestParam String courseId);
}
