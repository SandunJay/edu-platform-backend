package com.lms.microservices.contentsservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "contents")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Content {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
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
    @Column(name = "createdDate", nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "lastUpdatedDate", nullable = false)
    private Date lastUpdatedDate;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        this.createdDate = now;
        this.lastUpdatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedDate = new Date();
    }
}
