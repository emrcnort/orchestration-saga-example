package com.saga.example.payment.entity;

import com.saga.example.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Double amount;
    private Date paymentDate;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
