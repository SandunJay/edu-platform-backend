package com.sanjay.LearnerService.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LearnerService {
    ResponseEntity<List<Object>> getEnrolledCourseDetails(String userId);
}
