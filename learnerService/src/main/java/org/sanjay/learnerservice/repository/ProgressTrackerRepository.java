package org.sanjay.learnerservice.repository;

import org.sanjay.learnerservice.entity.ProgressTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressTrackerRepository extends JpaRepository<ProgressTracker, Long> {
}
