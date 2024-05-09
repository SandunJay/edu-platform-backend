package com.techie.adminService.dto;

import java.math.BigDecimal;

public record CourseRequest(Long id, String name, String description, BigDecimal price) {
}
