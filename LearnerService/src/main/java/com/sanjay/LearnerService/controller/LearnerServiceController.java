package com.sanjay.LearnerService.controller;

import com.sanjay.LearnerService.service.LearnerService;
import com.sanjay.LearnerService.service.serviceImpl.LearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/learner")
public class LearnerServiceController {
    private final LearnerService learnerService;

    @Autowired
    public LearnerServiceController(LearnerService learnerService) {
        this.learnerService = learnerService;
    }

    @GetMapping("/enrolled-courses")
    public ResponseEntity<List> getEnrolledCourses(@RequestParam String userId) {
        return learnerService.getEnrolledCourseDetails(userId);
    }
}
