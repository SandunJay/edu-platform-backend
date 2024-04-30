package com.lms.microservices.course.controller;

import com.lms.microservices.course.dto.CourseRequest;
import com.lms.microservices.course.dto.CourseResponse;
import com.lms.microservices.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor

public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@RequestBody CourseRequest courseRequest) {

        return courseService.createCourse(courseRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/by-courseId/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourseByCourseId(@PathVariable String courseId) {
        return courseService.getCourseByCourseId(courseId);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourse(@PathVariable String id, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable String id) {
    courseService.deleteCourse(id);
    }
}
