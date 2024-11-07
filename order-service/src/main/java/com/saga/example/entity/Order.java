package com.saga.example.entity;

import com.saga.example.enums.OrderStatus;
import jakarta.persistence.*;


@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private Integer quantity;
    private Double price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
