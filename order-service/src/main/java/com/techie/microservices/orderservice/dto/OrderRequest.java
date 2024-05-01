package com.techie.microservices.orderservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public record OrderRequest(Long id, String orderNumber, Set<Long> courseIds, String userId , BigDecimal totalPrice, Date orderDate) {
    public OrderRequest {
    }
}
