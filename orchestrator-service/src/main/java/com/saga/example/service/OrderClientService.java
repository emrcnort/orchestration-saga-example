package com.saga.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order-service", url = "http://localhost:8081")
public class OrderClientService {
    @PostMapping("/orders/confirm/{id}")
    public void confirmBooking(@PathVariable("id") Long orderId) {
    }
    @PostMapping("/orders/cancel/{id}")
    public void cancelBooking(@PathVariable("id") Long orderId) {
    }
}
