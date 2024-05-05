package com.sanjay.LearnerService.client;

import com.sanjay.LearnerService.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "courseService", url = "${courseService.url}")
public interface CourseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/course/by-courseId/{courseId}")
    CourseResponse getCourseDetails(@PathVariable("courseId") String courseId);
}
