package com.lms.microservices.course.dto;

import java.math.BigDecimal;


public record CourseRequest(String id,String courseId, String name, String author,String description,String learningoutcome,String category, BigDecimal price,String imageurl) {
}