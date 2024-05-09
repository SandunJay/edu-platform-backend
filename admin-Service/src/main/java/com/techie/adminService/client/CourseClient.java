package com.techie.adminService.client;

import com.techie.adminService.dto.CourseRequest;
import com.techie.adminService.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "course-service" , url = "http://localhost:8093")
public interface CourseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/course")
    List<CourseResponse> getAllCourses();

    @RequestMapping(method = RequestMethod.GET, value = "/api/course/{id}")
    CourseResponse getCourseById(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/api/course/{id}")
    CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/course/{id}")
    void deleteCourse(@PathVariable Long id);
}
