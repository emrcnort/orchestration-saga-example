package com.saga.example.jpa;

import com.saga.example.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByOrderId(Long bookingId);
}
