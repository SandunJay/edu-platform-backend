package com.techie.adminService.service;

import com.techie.adminService.client.CourseClient;
import com.techie.adminService.client.OrderClient;
import com.techie.adminService.client.UserClient;
import com.techie.adminService.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminTaskService {

    private final RestTemplate restTemplate;
    private final CourseClient courseClient;
    private final OrderClient orderClient;
    private final UserClient userClient;

    //--------------------------------course--------------
    public List<CourseResponse> getCourses() {
        return courseClient.getAllCourses();
    }
    public CourseResponse getCourseById( Long id) {
        return courseClient.getCourseById(id);
    }

    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {
        return courseClient.updateCourse(id, courseRequest);
    }

    public void deleteCourse(Long id) {
        courseClient.deleteCourse(id);
    }
    //--------------------------------user--------------
    public List<UserResponse> getUsers() {
        return userClient.getAllUsers();
    }

    public UserResponse getUserById(Long id) {
        return userClient.getUserById(id);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        return userClient.updateUser(id, userRequest);
    }

    public void deleteUser(Long id) {
        userClient.deleteUser(id);
    }


    //--------------------------------order--------------
    public List<OrderResponse> getOrders() {
        return orderClient.getAllOrders();
    }

    public OrderResponse getOrderById(Long id) {
        return orderClient.getOrderById(id);
    }
}