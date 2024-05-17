package com.sanjay.LearnerService.service.serviceImpl;

import com.sanjay.LearnerService.dto.*;
import com.sanjay.LearnerService.client.ContentClient;
import com.sanjay.LearnerService.client.CourseClient;
import com.sanjay.LearnerService.client.EnrollmentClient;
import com.sanjay.LearnerService.service.LearnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class LearnerServiceImpl implements LearnerService {
    private final EnrollmentClient enrollmentClient;
    private final CourseClient courseClient;
    private final ContentClient contentClient;


    @Override
    public ResponseEntity<CourseDetailsMapDTO> getEnrolledCourseDetails(String userId) {
        ResponseEntity<List<EnrollmentDTO>> enrollmentResponse = enrollmentClient.getEnrollmentsByUserId(userId);

        List<EnrollmentDTO> enrollments = enrollmentResponse.getBody();
        if (enrollments == null || enrollments.isEmpty()) {
            return ResponseEntity.ok(new CourseDetailsMapDTO(Collections.emptyMap()));
        }

        Map<String, CourseResponse> courseDetailsMap = new HashMap<>();

        for (EnrollmentDTO enrollment : enrollments) {
            if (enrollment.getEnrollmentItemsList() != null) {
                for (EnrollmentItemsDTO item : enrollment.getEnrollmentItemsList()) {
                    String courseId = item.getCourseId();
                    if (courseId != null && !courseDetailsMap.containsKey(courseId)) {
                        try {
                            CourseResponse courseResponse = courseClient.getCourseDetails(courseId);
                            courseDetailsMap.put(courseId, courseResponse);
                        } catch (Exception e) {
                            courseDetailsMap.put(courseId, null);
                        }
                    }
                }
            }
        }
        return ResponseEntity.ok(new CourseDetailsMapDTO(courseDetailsMap));
    }

    public ResponseEntity<ContentDetailsMapDTO> getEnrolledCourseContent(String courseId) {
        Map<String, ContentResponse> contentDetailsMap = new HashMap<>();

        try {
            List<ContentResponse> contentResponses = contentClient.getCourseContent(courseId);

            if (contentResponses != null) {
                for (ContentResponse content : contentResponses) {
                    contentDetailsMap.put(content.id(), content);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ContentDetailsMapDTO(contentDetailsMap));
    }


    @Override
    public EnrollmentDTO updateProgress(Long enrollmentId, String contentId) {

        try{
            ContentResponse contentResponse = contentClient.getContentById(contentId);
            String courseId = contentResponse.courseId();

            if (contentResponse == null) {
                throw new RuntimeException("Content not found");
            }

            ProgressTrackerDTO progressTrackerDTO = new ProgressTrackerDTO();
            progressTrackerDTO.setContentId(contentId);
            progressTrackerDTO.setAddedDate(LocalDateTime.now());
            progressTrackerDTO.setLastUpdatedDate(LocalDateTime.now());

            try{
                return enrollmentClient.updateProgress(enrollmentId, courseId, progressTrackerDTO);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public float getProgressPercentage(Long enrollmentId) {
        return 0;
    }

}
