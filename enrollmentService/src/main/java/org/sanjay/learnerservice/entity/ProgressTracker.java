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

    @Column(name = "content_id")
    private String contentId;

    @Column(name = "added_date")
    private LocalDateTime addedDate;

    @Column(name = "last_updated_date")
    private LocalDateTime lastUpdatedDate;
}
