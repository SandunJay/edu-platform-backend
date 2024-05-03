package org.sanjay.learnerservice.controller;

import org.sanjay.learnerservice.dto.EnrollmentDTO;
import org.sanjay.learnerservice.dto.EnrollmentItemsDTO;
import org.sanjay.learnerservice.dto.ProgressTrackerDTO;
import org.sanjay.learnerservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;
    @PostMapping
    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody EnrollmentDTO enrollmentDTO) {
        System.out.println("EnrollmentDTO: " + enrollmentDTO);
        EnrollmentDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        System.out.println("Created Enrollment: " + createdEnrollment);
        return ResponseEntity.ok(createdEnrollment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
        EnrollmentDTO enrollmentDTO = enrollmentService.getEnrollment(id);
        return ResponseEntity.ok(enrollmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        List<EnrollmentDTO> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByUserId(@PathVariable String userId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByUserId(userId);
        return ResponseEntity.ok(enrollments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO updatedEnrollment = enrollmentService.updateEnrollment(id, enrollmentDTO);
        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{enrollmentId}/items")
    public ResponseEntity<EnrollmentItemsDTO> addEnrollmentItem(@PathVariable Long enrollmentId, @RequestBody EnrollmentItemsDTO enrollmentItemsDTO) {
        EnrollmentItemsDTO addedEnrollmentItem = enrollmentService.addEnrollmentItems(enrollmentId, enrollmentItemsDTO);
        return ResponseEntity.ok(addedEnrollmentItem);
    }

    @PatchMapping("/{enrollmentId}/courses/{courseId}/progress")
    public ResponseEntity<EnrollmentDTO> updateProgress(
            @PathVariable Long enrollmentId,
            @PathVariable String courseId,
            @RequestBody ProgressTrackerDTO progressTrackerDTO) {

        EnrollmentDTO updatedEnrollment = enrollmentService.updateProgressTracker(
                enrollmentId,
                courseId,
                progressTrackerDTO);

        return ResponseEntity.ok(updatedEnrollment);
    }
}
