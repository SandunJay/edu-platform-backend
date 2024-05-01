package com.sanjay.LearnerService.client;

import com.sanjay.LearnerService.DTO.EnrollmentDTO;
import com.sanjay.LearnerService.DTO.ProgressTrackerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "enrollmentService", url = "${enrollmentService.url}")
public interface EnrollmentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/enrollment/user/{userId}")
    ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByUserId(@PathVariable("userId") String userId);

    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/api/v1/enrollment/{enrollmentId}/courses/{courseId}/progress"
    )
    EnrollmentDTO updateProgress(
            @PathVariable("enrollmentId") Long enrollmentId,
            @PathVariable("courseId") String courseId,
            @RequestBody ProgressTrackerDTO progressTrackerDTO
    );

}
