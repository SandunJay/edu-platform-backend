package com.sanjay.LearnerService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO {
    private Long id;
    private String userId;
    private LocalDateTime enrollmentDate;
    private List<EnrollmentItemsDTO> enrollmentItemsList = new ArrayList<>();
}
