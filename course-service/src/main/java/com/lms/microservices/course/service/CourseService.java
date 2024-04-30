package com.lms.microservices.course.service;




import com.lms.microservices.course.dto.CourseRequest;
import com.lms.microservices.course.dto.CourseResponse;
import com.lms.microservices.course.exception.ResourceNotFoundException;
import com.lms.microservices.course.model.Course;
import com.lms.microservices.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseResponse createCourse(CourseRequest courseRequest) {
        Course course = Course.builder()
                .name(courseRequest.name())
                .description(courseRequest.description())
                .price(courseRequest.price())
                .build();
        courseRepository.save(course);
        log.info("Course created successfully");
        return new CourseResponse(course.getId(), course.getName(), course.getDescription(), course.getPrice());
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseResponse(course.getId(), course.getName(), course.getDescription(), course.getPrice()))
                .toList();
    }

    public CourseResponse getCourseById(String id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        log.info("Course found successfully");
        return new CourseResponse(course.getId(), course.getName(), course.getDescription(), course.getPrice());
    }

    public CourseResponse updateCourse(String id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        course.setName(courseRequest.name());
        course.setDescription(courseRequest.description());
        course.setPrice(courseRequest.price());
        courseRepository.save(course);
        log.info("Course updated successfully");
        return new CourseResponse(course.getId(), course.getName(), course.getDescription(), course.getPrice());
    }

    public void deleteCourse(String id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        courseRepository.delete(course);
        log.info("Course deleted successfully");
    }

}
