package org.sanjay.learnerservice.repository;

import org.sanjay.learnerservice.entity.EnrollmentItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentItemsRepository extends JpaRepository<EnrollmentItems, Long> {
}
