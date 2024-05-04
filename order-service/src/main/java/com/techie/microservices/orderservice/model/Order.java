package com.techie.microservices.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;

    @ElementCollection
    @CollectionTable(name = "order_course_ids", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "course_id")
    private Set<Long> courseIds = new HashSet<>();//from course service

    private String userId;//from user service
    private BigDecimal totalPrice;

    @Temporal(TemporalType.DATE)
    private Date orderDate;
}
