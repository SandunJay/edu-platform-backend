package com.sanjay.LearnerService.DTO;

import java.util.Date;

public record ContentResponse(Long id, String title, String description, String courseId , Date createdDate, Date lastUpdatedDate) {
}
