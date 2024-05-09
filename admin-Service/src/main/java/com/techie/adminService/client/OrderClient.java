package com.techie.adminService.client;

import com.techie.adminService.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "order-service" , url = "http://localhost:8091")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/orders")
    List<OrderResponse> getAllOrders();

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/order/{id}")
    OrderResponse getOrderById(@PathVariable("id") Long orderId);
}
