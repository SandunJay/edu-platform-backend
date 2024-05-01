package com.lms.microservices.contentsservice.dto;

import java.util.Date;

public record ContentRequest(String id, String title, String description, String courseId ,Date createdDate, Date lastUpdatedDate){
}
