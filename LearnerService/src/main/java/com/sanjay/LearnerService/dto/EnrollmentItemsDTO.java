package com.sanjay.LearnerService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentItemsDTO {
    private Long id;
    private String courseId;
    private boolean isCompleted;
    private List<ProgressTrackerDTO> progressTrackerItemsList;
}
