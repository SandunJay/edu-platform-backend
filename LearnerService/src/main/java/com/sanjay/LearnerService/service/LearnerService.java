package com.sanjay.LearnerService.service;

import com.sanjay.LearnerService.dto.ContentDetailsMapDTO;
import com.sanjay.LearnerService.dto.CourseDetailsMapDTO;
import com.sanjay.LearnerService.dto.EnrollmentDTO;
import org.springframework.http.ResponseEntity;

public interface LearnerService {
    public ResponseEntity<CourseDetailsMapDTO> getEnrolledCourseDetails(String userId);

    public ResponseEntity<ContentDetailsMapDTO> getEnrolledCourseContent(String courseId);

    EnrollmentDTO updateProgress(Long enrollmentId, String contentId);
}
