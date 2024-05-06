package com.techie.microservices.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "payment-service", url = "http://localhost:8082")
public interface PaymentClient {

    @RequestMapping(method = RequestMethod.POST, value = "/payment/create-session")
    Map<String, Object> createSession(@RequestBody Map<String, Object> request);

    @GetMapping("/payment/get-payment-intent")
    String getPaymentIntent(@RequestParam("sessionId") String sessionId);
}
