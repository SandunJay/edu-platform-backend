package com.lms.microservices.course.repository;

import com.lms.microservices.course.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String>{
    Optional<Course> findByCourseId(String courseId);

    boolean existsByCourseId(String courseId);

}
