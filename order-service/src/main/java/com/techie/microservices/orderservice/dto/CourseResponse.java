package com.techie.microservices.orderservice.dto;

import java.math.BigDecimal;

public record CourseResponse(Long id, String name, String description, BigDecimal price) {
}
