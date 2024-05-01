package com.sanjay.LearnerService.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressTrackerDTO {
    private Long id;
    private String contentId;
    private LocalDateTime addedDate;
    private LocalDateTime lastUpdatedDate;
}
