package com.techie.microservices.orderservice.client;

import com.techie.microservices.orderservice.dto.EnrollmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "enrollment-service", url = "http://localhost:8080") // replace with the actual service name and URL
public interface EnrollmentClient {

    @PostMapping("/enrollment")
    EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);
}
