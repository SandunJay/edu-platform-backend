package com.techie.microservices.orderservice.service;

import com.techie.microservices.orderservice.dto.OrderRequest;
import com.techie.microservices.orderservice.dto.OrderResponse;
import com.techie.microservices.orderservice.model.Order;
import com.techie.microservices.orderservice.repository.OrderRepositiory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepositiory orderRepositiory;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        // Validate courseIds
        Set<String> courseIds = orderRequest.courseIds();
        if (courseIds == null || courseIds.isEmpty()) {
            throw new IllegalArgumentException("At least one course ID must be provided.");
        }
        BigDecimal totalPrice = calculateTotalPrice(courseIds);


        //map order request to order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setTotalPrice(totalPrice);
        order.setCourseIds(courseIds);
        order.setUserId(orderRequest.userId());
        order.setOrderDate(orderRequest.orderDate());

        //save order to order repository
        orderRepositiory.save(order);
        log.info("Order Placed successfully");
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getCourseIds(), order.getUserId(), order.getTotalPrice(), order.getOrderDate());
    }

    private BigDecimal calculateTotalPrice(Set<String> courseIds) {
        // Dummy logic to calculate total price based on course prices
        BigDecimal totalPrice = BigDecimal.ZERO;
        // Fetch course prices and calculate total price
        for (String courseId : courseIds) {
            // Fetch course price from course service or database
            BigDecimal coursePrice = fetchCoursePrice(courseId);
            totalPrice = totalPrice.add(coursePrice);
        }
        return totalPrice;
    }

    private BigDecimal fetchCoursePrice(String courseId) {
        // Dummy method to fetch course price, replace with actual logic
        // For simplicity, returning a fixed price
//        Optional<Course> courseOptional = courseRepository.findById(courseId);
//        if (courseOptional.isPresent()) {
//            Course course = courseOptional.get();
//            return course.getPrice();
//        } else {
//            throw new RuntimeException("Course not found");
//        }
        return BigDecimal.valueOf(100);
    }

    public List<OrderResponse> getAllOrders(){
        return orderRepositiory.findAll()
                .stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getOrderNumber(),
                        order.getCourseIds(),
                        order.getUserId(),
                        order.getTotalPrice(),
                        order.getOrderDate()
                ))
                .toList();
    }

    public OrderResponse getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepositiory.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return new OrderResponse(
                    order.getId(),
                    order.getOrderNumber(),
                    order.getCourseIds(),
                    order.getUserId(),
                    order.getTotalPrice(),
                    order.getOrderDate()
            );
        } else {
            log.error("Order with ID {} not found", orderId);
            throw new RuntimeException("Order not found");
        }
    }
}
