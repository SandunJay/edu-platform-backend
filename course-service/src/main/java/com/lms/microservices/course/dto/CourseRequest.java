package com.lms.microservices.course.dto;

import java.math.BigDecimal;

public record CourseRequest(String id, String name, String description, BigDecimal price) {
}
