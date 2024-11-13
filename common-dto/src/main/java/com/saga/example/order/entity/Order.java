package com.saga.example.order.entity;

import com.saga.example.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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
