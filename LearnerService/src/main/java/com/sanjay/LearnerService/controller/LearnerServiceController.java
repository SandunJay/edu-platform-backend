package com.sanjay.LearnerService.controller;

import com.sanjay.LearnerService.dto.ContentDetailsMapDTO;
import com.sanjay.LearnerService.dto.CourseDetailsMapDTO;
import com.sanjay.LearnerService.dto.EnrollmentDTO;
import com.sanjay.LearnerService.service.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
