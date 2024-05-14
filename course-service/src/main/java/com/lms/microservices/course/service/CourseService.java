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
                .courseId(generateUniqueId(courseRequest.category()))
                .name(courseRequest.name())
                .author(courseRequest.author())
                .description(courseRequest.description())
                .learningoutcome(courseRequest.learningoutcome())
                .category(courseRequest.category())
                .price(courseRequest.price())
                .imageurl(courseRequest.imageurl())
                .build();
        courseRepository.save(course);
        log.info("Course created successfully");
        return new CourseResponse(course.getId(), course.getCourseId(),course.getName(),course.getAuthor(), course.getDescription(),course.getLearningoutcome(),course.getCategory(),course.getPrice(),course.getImageurl());
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(course -> new CourseResponse(course.getId(),course.getCourseId(), course.getName(),course.getAuthor(), course.getDescription(),course.getLearningoutcome(),course.getCategory(), course.getPrice(),course.getImageurl()))
                .toList();
    }

    public CourseResponse getCourseById(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        log.info("Course found successfully");
        return new CourseResponse(course.getId(),course.getCourseId(), course.getName(),course.getAuthor(), course.getDescription(),course.getLearningoutcome(),course.getCategory(), course.getPrice(),course.getImageurl());
    }

    public CourseResponse getCourseByCourseId(String courseId) {
        try {
            Course course = courseRepository.findByCourseId(courseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found with courseId " + courseId));

            if (course == null) {
                log.info("Course not found with courseId " + courseId);
                return null;
            }

            log.info("Course found successfully with courseId " + courseId);
            return new CourseResponse(course.getId(), course.getCourseId(), course.getName(), course.getAuthor(), course.getDescription(),course.getLearningoutcome(),course.getCategory(), course.getPrice(), course.getImageurl());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 public CourseResponse updateCourse(String courseId, CourseRequest courseRequest) {
    Course course = courseRepository.findByCourseId(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));

    if (courseRequest.name() != null) {
        course.setName(courseRequest.name());
    }
    if (courseRequest.description() != null) {
        course.setDescription(courseRequest.description());
    }
    if (courseRequest.price() != null) {
        course.setPrice(courseRequest.price());
    }
    if (courseRequest.learningoutcome() != null) {
        course.setLearningoutcome(courseRequest.learningoutcome());
    }
    courseRepository.save(course);
    log.info("Course updated successfully");
    return new CourseResponse(course.getId(), course.getCourseId(),course.getName(),course.getAuthor(), course.getDescription(),course.getLearningoutcome(),course.getCategory(), course.getPrice(),course.getImageurl());
}
    public void deleteCourse(String courseId) {
        Course course = courseRepository.findByCourseId(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
        courseRepository.delete(course);
        log.info("Course deleted successfully");
    }
    public boolean existsCourseById(String id) {
        return courseRepository.existsByCourseId(id);
    }

    public String generateUniqueId(String category) {
    String prefix = switch (category) {
        case "Information Technology" -> "IT";
        case "Engineering" -> "E";
        case "Business Management" -> "BM";
        case "Arts" -> "A";
        default -> throw new IllegalArgumentException("Invalid category");
    };

        String id;
    do {
        id = prefix + (int)(Math.random() * 10000);
    } while(courseRepository.existsById(id));
    return id;
}
}
