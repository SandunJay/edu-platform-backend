package org.sanjay.learnerservice.repository;

import org.sanjay.learnerservice.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
    List<Enrollment> findByUserId(String    userId);
}
