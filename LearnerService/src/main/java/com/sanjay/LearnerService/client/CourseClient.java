package com.sanjay.LearnerService.client;

import com.sanjay.LearnerService.DTO.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "courseService", url = "${courseService.url}")
public interface CourseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/courses")
    CourseResponse getCourseDetails(@RequestParam("courseId") String courseId);
}
