package com.sanjay.LearnerService.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LearnerService {
    public ResponseEntity<List<String>> getEnrolledCourseDetails(String userId);
}
