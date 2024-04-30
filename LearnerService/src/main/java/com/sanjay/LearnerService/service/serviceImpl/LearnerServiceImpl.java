package com.sanjay.LearnerService.service.serviceImpl;

import com.sanjay.LearnerService.DTO.EnrollmentDTO;
import com.sanjay.LearnerService.DTO.EnrollmentItemsDTO;
import com.sanjay.LearnerService.client.EnrollmentClient;
import com.sanjay.LearnerService.service.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnerServiceImpl implements LearnerService {
    private final EnrollmentClient enrollmentClient;

    @Override
    public ResponseEntity<List<String>> getEnrolledCourseDetails(String userId) {
        ResponseEntity<List<EnrollmentDTO>> enrollmentResponse = enrollmentClient.getEnrollmentsByUserId(userId);

        List<EnrollmentDTO> enrollments = enrollmentResponse.getBody();

        if (enrollments == null || enrollments.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        Set<String> uniqueCourseIds = new HashSet<>();
        for (EnrollmentDTO enrollment : enrollments) {
            if (enrollment.getEnrollmentItemsList() != null) {
                for (EnrollmentItemsDTO item : enrollment.getEnrollmentItemsList()) {
                    if (item.getCourseId() != null) {
                        uniqueCourseIds.add(item.getCourseId());
                    }
                }
            }
        }

        List<String> courseIdsList = new ArrayList<>(uniqueCourseIds);
        return ResponseEntity.ok(courseIdsList);
    }
}
