package com.saga.example.service;

import com.saga.example.order.entity.Order;
import com.saga.example.payment.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderOrchestratorService {
    private final OrderClientService orderService;
    private final PaymentClientService paymentService;

    @JmsListener(destination = "orderQueue")
    public void handleBooking(Order order) {

    }

    @JmsListener(destination = "paymentQueue")
    public void handlePayment(Payment payment) {
        try {
            // step 2: confirm payment is success or failed. If it's failed
            // It's failure, throw exception and rollback.
            paymentService.confirmPayment(payment.getOrderId());

            // Step 3: Mark status is comfirmed in booking.
            orderService.confirmBooking(payment.getOrderId());
        } catch (Exception e) {
            // Handle exceptions and initiate compensation
            orderService.cancelBooking(payment.getOrderId());
            paymentService.cancelPayment(payment.getOrderId());
        }
    }
}
