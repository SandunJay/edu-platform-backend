package com.sanjay.LearnerService.DTO;

import java.util.Map;

public record CourseDetailsMapDTO(Map<String, CourseResponse> courseDetails) {
}
