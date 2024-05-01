package com.techie.microservices.orderservice.client;

import com.techie.microservices.orderservice.dto.CourseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "course-service" , url = "http://localhost:8093")
public interface CourseClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/course/{id}")
    CourseResponse getCourseById(@PathVariable Long id);

}
