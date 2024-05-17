package com.lms.microservices.contentsservice.repositoty;

import com.lms.microservices.contentsservice.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository <Content, Long>{

    List<Content> findByCourseId(String courseId);

    boolean existsById(String id);

    List<Content> findById(String id);


}
