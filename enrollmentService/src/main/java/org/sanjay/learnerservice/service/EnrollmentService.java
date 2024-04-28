package org.sanjay.learnerservice.service;

import org.sanjay.learnerservice.dto.EnrollmentDTO;
import org.sanjay.learnerservice.dto.EnrollmentItemsDTO;
import org.sanjay.learnerservice.dto.ProgressTrackerDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);

    EnrollmentDTO getEnrollment(Long id);

    List<EnrollmentDTO> getAllEnrollments();

    EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO);

    void deleteEnrollment(Long id);

    EnrollmentItemsDTO addEnrollmentItems(Long enrollmentId, EnrollmentItemsDTO enrollmentItemsDTO);

//    ProgressTrackerDTO patchProgressTracker(Long progressTrackerId, ProgressTrackerDTO progressTrackerDTO);

//    ProgressTrackerDTO addProgressTracker(Long enrollmentId, String courseId, ProgressTrackerDTO progressTrackerDTO);

    EnrollmentDTO updateProgressTracker(Long enrollmentId, String courseId, ProgressTrackerDTO progressTrackerDTO);
}
