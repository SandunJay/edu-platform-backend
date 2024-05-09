package com.userproject.demo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/i-controller")
public class InstructorDashboard {
    @GetMapping
    public ResponseEntity<String> sayHelloInstructor() {
        return ResponseEntity.ok("Hello from Instructor dashboard");
    }
}
