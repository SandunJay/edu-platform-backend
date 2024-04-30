package com.sanjay.LearnerService.DTO;

import java.util.Map;

// A DTO to store course details with course IDs as keys
public record CourseDetailsMapDTO(Map<String, CourseResponse> courseDetails) {
}
