package com.sanjay.LearnerService.client;

import com.sanjay.LearnerService.DTO.EnrollmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "enrollmentService", url = "${enrollmentService.url}")
public interface EnrollmentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/enrollment/user/{userId}")
    ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByUserId(@PathVariable("userId") String userId);
}
