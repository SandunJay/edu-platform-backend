package com.sanjay.LearnerService.service.serviceImpl;

import com.sanjay.LearnerService.DTO.EnrollmentDTO;
import com.sanjay.LearnerService.DTO.EnrollmentItemsDTO;
import com.sanjay.LearnerService.client.CourseClient;
import com.sanjay.LearnerService.client.EnrollmentClient;
import com.sanjay.LearnerService.service.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnerServiceImpl implements LearnerService {
    private final EnrollmentClient enrollmentClient;
    private final CourseClient courseClient;

    public ResponseEntity<List<Object>> getEnrolledCourseDetails(String userId) {
        // Fetch all enrollments for the given user ID
        ResponseEntity<List<EnrollmentDTO>> enrollmentResponse = enrollmentClient.getEnrollmentsByUserId(userId);

        // Extract the list of enrollments from the response
        List<EnrollmentDTO> enrollments = enrollmentResponse.getBody();

        // Check for null or empty enrollments to avoid errors
        if (enrollments == null || enrollments.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        // Fetch course details for each enrolled course
        List<ResponseEntity> courseDetails = new ArrayList<>();
        for (EnrollmentDTO enrollment : enrollments) {
            if (enrollment.getEnrollmentItemsList() == null || enrollment.getEnrollmentItemsList().isEmpty()) {
                continue;
            }

            for (EnrollmentItemsDTO item : enrollment.getEnrollmentItemsList()) {
                ResponseEntity<Object> courseResponse = courseClient.getCourseDetails(item.getCourseId());
                Object course = (courseResponse != null) ? courseResponse.getBody() : null;

                if (course != null) {
                    courseDetails.add(course);
                }
            }
        }

        // Return the list of course details
        return ResponseEntity.ok(courseDetails);
    }
}
