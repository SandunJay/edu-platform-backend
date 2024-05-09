package com.userproject.demo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/d-controller")
public class DashboardController {
//    @GetMapping("/dashboard")
//    public String dashboard() {
//        // Return the name of the template for the dashboard
//        return "dashboard"; //  "dashboard" is the name of my Thymeleaf/HTML template
//    }
    @GetMapping
    public ResponseEntity<String> sayHelloDefault() {
        return ResponseEntity.ok("Hello from Default dashboard");
    }
}
