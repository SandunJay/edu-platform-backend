package com.sanjay.LearnerService.controller;

import com.sanjay.LearnerService.DTO.ContentDetailsMapDTO;
import com.sanjay.LearnerService.DTO.CourseDetailsMapDTO;
import com.sanjay.LearnerService.DTO.CourseResponse;
import com.sanjay.LearnerService.DTO.EnrollmentDTO;
import com.sanjay.LearnerService.service.LearnerService;
import com.sanjay.LearnerService.service.serviceImpl.LearnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/learner")
@RequiredArgsConstructor
public class LearnerServiceController {
    private final LearnerService learnerService;

//    @GetMapping("/enrolled-courses")
//    public ResponseEntity<List<CourseResponse>> getEnrolledCourses(@RequestParam String userId) {
//        return learnerService.getEnrolledCourseDetails(userId);
//    }

    @GetMapping("/enrolled-courses")
    public ResponseEntity<CourseDetailsMapDTO> getEnrolledCourses(@RequestParam String userId) {
        return learnerService.getEnrolledCourseDetails(userId);
    }

    @GetMapping("/course-content")
    public ResponseEntity<ContentDetailsMapDTO> getCourseContent(@RequestParam String courseId) {
        return learnerService.getEnrolledCourseContent(courseId);
    }

    @PatchMapping("/enrollments/{enrollmentId}/content/{contentId}")
    public ResponseEntity<EnrollmentDTO> patchWatchedContent(@PathVariable Long enrollmentId, @PathVariable String contentId) {

        EnrollmentDTO updatedEnrollment = learnerService.updateProgress(enrollmentId, contentId);

        return ResponseEntity.ok(updatedEnrollment);
    }


}
