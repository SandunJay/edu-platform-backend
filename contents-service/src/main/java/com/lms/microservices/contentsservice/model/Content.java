package com.lms.microservices.contentsservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "contents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    @Id
    private String id;
    private String title;
    private String description;

    @Column(name = "courseId")
    private String courseId;

    @Column(name = "videoUrl")
    private String videoUrl;

    @Column(name = "pdfUrl")
    private String pdfUrl;

    @Column(name = "status")
    private boolean status;



    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date lastUpdatedDate;

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        lastUpdatedDate = new Date();
    }
}
