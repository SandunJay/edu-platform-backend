package org.sanjay.learnerservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "enrollment_items")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseId;

    private boolean isCompleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "enrollment_item_id")
    private List<ProgressTracker> progressTrackerItemsList;
}
