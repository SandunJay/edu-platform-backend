package org.sanjay.learnerservice.repository;

import org.sanjay.learnerservice.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
}
