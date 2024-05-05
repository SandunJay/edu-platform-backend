package com.sanjay.LearnerService.dto;

import java.math.BigDecimal;

public record CourseResponse(String id, String name,String author, String description, BigDecimal price) {
}
