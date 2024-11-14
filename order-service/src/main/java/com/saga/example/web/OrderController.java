package com.saga.example.web;

import com.saga.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("confirm/{id}")
    public void confirmBooking(@PathVariable Long id) {
        orderService.confirmBooking(id);
    }

    @PostMapping("cancel/{id}")
    public void cancelBooking(@PathVariable Long id) {
        orderService.cancelBooking(id);
    }
}
