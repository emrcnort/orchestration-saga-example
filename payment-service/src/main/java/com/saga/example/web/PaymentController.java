package com.saga.example.web;

import com.saga.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("confirm/{orderId}")
    public void confirmPayment(@PathVariable Long orderId) {
         paymentService.confirmPayment(orderId);
    }

    @PostMapping("cancel/{orderId}")
    public void cancelPayment(@PathVariable Long orderId) {
        paymentService.cancelPayment(orderId);
    }
}
