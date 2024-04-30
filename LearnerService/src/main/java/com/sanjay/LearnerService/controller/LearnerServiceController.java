package com.sanjay.LearnerService.controller;

import com.sanjay.LearnerService.DTO.CourseResponse;
import com.sanjay.LearnerService.service.LearnerService;
import com.sanjay.LearnerService.service.serviceImpl.LearnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/learner")
@RequiredArgsConstructor
public class LearnerServiceController {
    private final LearnerService learnerService;

    @GetMapping("/enrolled-courses")
    public ResponseEntity<List<CourseResponse>> getEnrolledCourses(@RequestParam String userId) {
        return learnerService.getEnrolledCourseDetails(userId);
    }
}
