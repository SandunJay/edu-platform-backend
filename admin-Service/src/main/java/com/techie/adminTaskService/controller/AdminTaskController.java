package com.techie.adminTaskService.controller;

import com.techie.adminTaskService.dto.*;
import com.techie.adminTaskService.service.AdminTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminTaskController {

    private final AdminTaskService adminTaskService;

    //----------------------------------courses----------------------------------
    @GetMapping("/courses")
    public List<CourseResponse> getCourses() {
        return adminTaskService.getCourses();
    }

    @GetMapping("/courses/{id}")
    public CourseResponse courseById(@PathVariable Long id) {
        return adminTaskService.getCourseById(id);
    }

    @PutMapping("/courses/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        return adminTaskService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        adminTaskService.deleteCourse(id);
    }

    //----------------------------------users----------------------------------

    @GetMapping("/users")
    public List<UserResponse> getUsers() {

        return adminTaskService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return adminTaskService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return adminTaskService.updateUser(id, userRequest);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        adminTaskService.deleteUser(id);
    }

    //----------------------------------orders----------------------------------

    @GetMapping("/orders")
    public List<OrderResponse> getOrders() {

        return adminTaskService.getOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return adminTaskService.getOrderById(id);
    }
}
