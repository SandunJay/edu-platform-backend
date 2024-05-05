package com.sanjay.LearnerService.dto;

import java.util.Map;

public record CourseDetailsMapDTO(Map<String, CourseResponse> courseDetails) {
}
