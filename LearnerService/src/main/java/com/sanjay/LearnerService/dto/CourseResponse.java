package com.sanjay.LearnerService.DTO;

import java.math.BigDecimal;

public record CourseResponse(String id, String name, String description, BigDecimal price) {
}
