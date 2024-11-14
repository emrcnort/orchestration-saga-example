package com.saga.example.service;

import com.saga.example.payment.entity.Payment;
import com.saga.example.payment.enums.PaymentStatus;
import com.saga.example.jpa.PaymentRepository;
import com.saga.example.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final JmsTemplate jmsTemplate;

    private final PaymentRepository paymentRepository;

    @Transactional
    public void processPayment(Order order) {
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setAmount(calculatePaymentAmount(order));
        paymentRepository.save(payment);
        jmsTemplate.convertAndSend("paymentQueue", payment);
    }

    @Transactional
    public void confirmPayment(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment != null) {
            payment.setStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);
        }
    }

    @Transactional
    public void cancelPayment(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment != null) {
            paymentRepository.delete(payment);
        }
    }

    private double calculatePaymentAmount(Order booking) {
        // Implement your payment calculation logic here
        return booking.getPrice() * booking.getQuantity();
    }
}
