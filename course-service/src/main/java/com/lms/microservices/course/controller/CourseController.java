package com.lms.microservices.course.controller;

import com.lms.microservices.course.dto.CourseRequest;
import com.lms.microservices.course.dto.CourseResponse;
import com.lms.microservices.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(value = "http://localhost:3000")
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

    @GetMapping("/by-author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse getCourseByAuthor(@PathVariable String author) {
        return courseService.getCourseByAuthor(author);
    }


    @PutMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourse(@PathVariable String courseId, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseId, courseRequest);
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
    }

    @GetMapping("/{courseId}/exists")
    @ResponseStatus(HttpStatus.OK)
    public boolean existsCourseById(@PathVariable String courseId) {
        return courseService.existsCourseById(courseId);
    }

}
