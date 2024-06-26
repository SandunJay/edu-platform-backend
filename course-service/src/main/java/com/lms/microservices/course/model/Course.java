package com.lms.microservices.course.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Course {
    @Id
    private String id;
    private String courseId;
    private String name;
    private String author;
    private String description;
    private String learningoutcome;
    private String category;
    private BigDecimal price;
    private String imageurl;

}
