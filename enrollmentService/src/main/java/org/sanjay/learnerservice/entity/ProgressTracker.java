package org.sanjay.learnerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress_tracker")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentId;
    private LocalDateTime addedDate;
    private LocalDateTime lastUpdatedDate;
}
