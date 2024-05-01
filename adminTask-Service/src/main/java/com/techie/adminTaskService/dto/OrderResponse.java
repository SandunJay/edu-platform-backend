package com.techie.adminTaskService.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public record OrderResponse(Long id, String orderNumber, Set<Long> courseIds, String userId , BigDecimal totalPrice, Date orderDate) {
}
