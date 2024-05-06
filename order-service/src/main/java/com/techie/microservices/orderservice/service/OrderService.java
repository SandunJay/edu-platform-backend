package com.techie.microservices.orderservice.service;

import com.techie.microservices.orderservice.client.CourseClient;
import com.techie.microservices.orderservice.client.EnrollmentClient;
import com.techie.microservices.orderservice.client.PaymentClient;
import com.techie.microservices.orderservice.client.UserClient;
import com.techie.microservices.orderservice.dto.*;
import com.techie.microservices.orderservice.model.Order;
import com.techie.microservices.orderservice.repository.OrderRepositiory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepositiory orderRepositiory;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private EnrollmentClient enrollmentClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private PaymentClient paymentClient;

    public OrderResponse placeOrder(OrderRequest orderRequest) {

        //get jwt token from userClient
        UserCookieResponse userCookieResponse = userClient.accessUserCookie();
        String jwtToken = userCookieResponse.token();

        // Validate userId
        String userEmail;
        try {
            boolean isValidUser = userClient.checkUserValidity(jwtToken);
            if (!isValidUser) {
                throw new IllegalArgumentException("Invalid user");
            }

            // Get user's email
            userEmail = userCookieResponse.email();

        } catch (Exception e) {
            log.error("User validation failed", e);
            throw new RuntimeException("User validation failed", e);
        }

        // Validate courseIds
        Set<Long> courseIds = orderRequest.courseIds();
        if (courseIds == null || courseIds.isEmpty()) {
            throw new IllegalArgumentException("At least one course ID must be provided.");
        }
        BigDecimal totalPrice = calculateTotalPrice(courseIds);


        //map order request to order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setTotalPrice(totalPrice);
        order.setCourseIds(courseIds);
        order.setUserId(userEmail);
        order.setOrderDate(orderRequest.orderDate());

        //save order to order repository
        orderRepositiory.save(order);
        log.info("Order Placed successfully");

        // Create payment session
        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("amount", orderRequest.totalPrice().multiply(new BigDecimal(100)).longValue()); // Stripe expects the amount in cents
        paymentRequest.put("productDescription", "Order " + order.getOrderNumber());
        Map<String, Object> paymentResponse = paymentClient.createSession(paymentRequest);
        String paymentSessionId = (String) paymentResponse.get("sessionId");

        // Check payment status
        String paymentIntentId = paymentClient.getPaymentIntent(paymentSessionId);
        if (paymentIntentId == null) {
            throw new RuntimeException("Payment failed");
        }

        // create EnrollmentDTO and set fields
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setUserId(order.getUserId());
        enrollmentDTO.setEnrollmentDate(order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        enrollmentDTO.setEnrollmentItemsList(order.getCourseIds().stream()
                .map(courseId -> {
                    EnrollmentItemsDTO item = new EnrollmentItemsDTO();
                    item.setCourseId(courseId.toString());
                    item.setCompleted(false);
                    return item;
                })
                .collect(Collectors.toList()));

        // call createEnrollment method
        enrollmentClient.createEnrollment(enrollmentDTO);


        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getCourseIds(), order.getUserId(), order.getTotalPrice(), order.getOrderDate());
    }


    private BigDecimal calculateTotalPrice(Set<Long> courseIds) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Long courseId : courseIds) {
            BigDecimal coursePrice = fetchCoursePrice(courseId);
            totalPrice = totalPrice.add(coursePrice);
        }
        return totalPrice;
    }

    private BigDecimal fetchCoursePrice(Long courseId) {
        CourseResponse courseResponse = courseClient.getCourseById(courseId);
        return courseResponse.price();
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
