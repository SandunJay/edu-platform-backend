package com.sanjay.LearnerService.service;

import com.sanjay.LearnerService.DTO.ContentDetailsMapDTO;
import com.sanjay.LearnerService.DTO.CourseDetailsMapDTO;
import com.sanjay.LearnerService.DTO.EnrollmentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LearnerService {
    public ResponseEntity<CourseDetailsMapDTO> getEnrolledCourseDetails(String userId);

    public ResponseEntity<ContentDetailsMapDTO> getEnrolledCourseContent(String courseId);

    EnrollmentDTO updateProgress(Long enrollmentId, String contentId);
}
