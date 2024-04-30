package com.lms.microservices.course.repository;

import com.lms.microservices.course.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String>{
}
