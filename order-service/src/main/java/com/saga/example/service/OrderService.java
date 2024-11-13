package com.saga.example.service;

import com.saga.example.enums.OrderStatus;
import com.saga.example.jpa.OrderRepository;
import com.saga.example.order.entity.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final JmsTemplate jmsTemplate;

    private final OrderRepository orderRepository;

    @Transactional
    public void makeBooking(Order order) {
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        jmsTemplate.convertAndSend("orderQueue", order);
    }

    @Transactional
    public void confirmBooking(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);
        }
    }

    @Transactional
    public void cancelBooking(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            orderRepository.delete(order);
        }
    }
}
