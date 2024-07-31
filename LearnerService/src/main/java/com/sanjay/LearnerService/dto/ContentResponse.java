package com.sanjay.LearnerService.dto;

import java.util.Date;

public record ContentResponse(
        String id,
        String title,
        String description,
        String courseId ,
        String videoUrl,
        String pdfUrl,
        Date createdDate,
        Date lastUpdatedDate) {
}
