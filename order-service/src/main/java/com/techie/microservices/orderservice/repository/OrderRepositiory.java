package com.techie.microservices.orderservice.repository;

import com.techie.microservices.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositiory extends JpaRepository<Order, Long>{
}
