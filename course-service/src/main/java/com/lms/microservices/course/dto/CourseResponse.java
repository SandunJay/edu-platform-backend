package com.lms.microservices.course.dto;

import java.math.BigDecimal;

public record CourseResponse(String id,String courseId, String name, String description, BigDecimal price) {
}
