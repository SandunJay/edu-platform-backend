package org.sanjay.learnerservice.service.serviceImpl;

import org.sanjay.learnerservice.dto.EnrollmentDTO;
import org.sanjay.learnerservice.dto.EnrollmentItemsDTO;
import org.sanjay.learnerservice.dto.ProgressTrackerDTO;
import org.sanjay.learnerservice.entity.Enrollment;
import org.sanjay.learnerservice.entity.EnrollmentItems;
import org.sanjay.learnerservice.entity.ProgressTracker;
import org.sanjay.learnerservice.repository.EnrollmentItemsRepository;
import org.sanjay.learnerservice.repository.EnrollmentRepository;
import org.sanjay.learnerservice.repository.ProgressTrackerRepository;
import org.sanjay.learnerservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private EnrollmentItemsRepository enrollmentItemsRepository;

    @Autowired
    private ProgressTrackerRepository progressTrackerRepository;

    @Override
    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
        if (enrollmentDTO == null) {
            throw new IllegalArgumentException("EnrollmentDTO must not be null");
        }

        if (enrollmentDTO.getEnrollmentItemsList() == null) {
            enrollmentDTO.setEnrollmentItemsList(new ArrayList<>());
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(enrollmentDTO.getUserId());
        enrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
        enrollment.setEnrollmentItemsList(enrollmentDTO.getEnrollmentItemsList()
                .stream()
                .map(this::convertToEnrollmentItems)
                .collect(Collectors.toList())
        );

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return convertToEnrollmentDTO(savedEnrollment);
    }

    @Override
    public EnrollmentDTO getEnrollment(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return convertToEnrollmentDTO(enrollment);
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.stream()
                .map(this::convertToEnrollmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByUserId(String userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);
        return enrollments.stream()
                .map(this::convertToEnrollmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO) {
       Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setUserId(enrollmentDTO.getUserId());
        enrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
        enrollment.setEnrollmentItemsList(
                enrollmentDTO.getEnrollmentItemsList()
                        .stream()
                        .map(this::convertToEnrollmentItems)
                        .collect(Collectors.toList())
        );

       Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
         return convertToEnrollmentDTO(updatedEnrollment);
    }

    @Override
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public EnrollmentItemsDTO addEnrollmentItems(Long enrollmentId, EnrollmentItemsDTO enrollmentItemsDTO) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new RuntimeException("Enrollment not found"));

        EnrollmentItems enrollmentItems = convertToEnrollmentItems(enrollmentItemsDTO);
        enrollment.getEnrollmentItemsList().add(enrollmentItems);

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return convertToEnrollmentItemsDTO(enrollmentItems);
    }

    @Override
    public EnrollmentDTO updateProgressTracker(Long enrollmentId, String courseId, ProgressTrackerDTO progressTrackerDTO) {
        // Step 1: Retrieve the correct Enrollment
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        // Step 2: Find the corresponding EnrollmentItem
        EnrollmentItems enrollmentItem = enrollment.getEnrollmentItemsList().stream()
                .filter(item -> courseId.equals(item.getCourseId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Enrollment item for course not found"));

        // Step 3: Initialize or find the ProgressTracker
        ProgressTracker progressTracker;

        if (enrollmentItem.getProgressTrackerItemsList() == null) {
            enrollmentItem.setProgressTrackerItemsList(new ArrayList<>());
        }

        if (progressTrackerDTO.getId() != null) {
            // If ID is provided, find and update the existing ProgressTracker
            progressTracker = enrollmentItem.getProgressTrackerItemsList().stream()
                    .filter(pt -> pt.getId().equals(progressTrackerDTO.getId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Progress tracker not found"));
        } else {
            // If ID is not provided, create a new ProgressTracker
            progressTracker = new ProgressTracker();
            // Step 4: Update ProgressTracker details
            progressTracker.setContentId(progressTrackerDTO.getContentId());
            progressTracker.setAddedDate(progressTrackerDTO.getAddedDate());
            progressTracker.setLastUpdatedDate(progressTrackerDTO.getLastUpdatedDate());
            enrollmentItem.getProgressTrackerItemsList().add(progressTracker);
        }

        // Step 5: Save the updated Enrollment and retrieve it
        enrollmentRepository.save(enrollment);

        Enrollment updatedEnrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return convertToEnrollmentDTO(updatedEnrollment);
    }


    private EnrollmentItems convertToEnrollmentItems(EnrollmentItemsDTO enrollmentItemsDTO) {
        EnrollmentItems enrollmentItems = new EnrollmentItems();
        enrollmentItems.setId(enrollmentItemsDTO.getId());
        enrollmentItems.setCourseId(enrollmentItemsDTO.getCourseId());
        enrollmentItems.setCompleted(enrollmentItemsDTO.isCompleted());
        return enrollmentItems;
    }

    private EnrollmentDTO convertToEnrollmentDTO(Enrollment enrollment) {
        // Ensure all lists are initialized to prevent lazy loading issues
        if (enrollment.getEnrollmentItemsList() == null) {
            enrollment.setEnrollmentItemsList(new ArrayList<>());
        }

        // Explicitly access the lists to trigger loading
        enrollment.getEnrollmentItemsList().forEach(item -> {
            if (item.getProgressTrackerItemsList() == null) {
                item.setProgressTrackerItemsList(new ArrayList<>());
            }
        });

        return EnrollmentDTO.builder()
                .id(enrollment.getId())
                .userId(enrollment.getUserId())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .enrollmentItemsList(
                        enrollment.getEnrollmentItemsList().stream()
                                .map(this::convertToEnrollmentItemsDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private EnrollmentItemsDTO convertToEnrollmentItemsDTO(EnrollmentItems enrollmentItems) {
        return EnrollmentItemsDTO.builder()
                .id(enrollmentItems.getId())
                .courseId(enrollmentItems.getCourseId())
                .isCompleted(enrollmentItems.isCompleted())
                .progressTrackerItemsList(
                        enrollmentItems.getProgressTrackerItemsList().stream()
                                .map(this::convertToProgressTrackerDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private ProgressTrackerDTO convertToProgressTrackerDTO(ProgressTracker progressTracker) {
        return ProgressTrackerDTO.builder()
                .id(progressTracker.getId())
                .contentId(progressTracker.getContentId())
                .addedDate(progressTracker.getAddedDate())
                .lastUpdatedDate(progressTracker.getLastUpdatedDate())
                .build();
    }
}
