package com.lms.microservices.contentsservice.dto;

import java.util.Date;

public record ContentRequest(String id, String title, String description, String courseId ,String videoUrl,String pdfUrl,boolean status,Date createdDate, Date lastUpdatedDate){
}
