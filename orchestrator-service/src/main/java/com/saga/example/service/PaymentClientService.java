package com.saga.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", url = "http://localhost:8082")
public class PaymentClientService {
    @PostMapping("/payments/confirm/{id}")
    public void confirmPayment(@PathVariable("orderId")  Long orderId) {
    }
    @PostMapping("/payments/cancel/{id}")
    public void cancelPayment(@PathVariable("orderId") Long orderId) {
    }
}
