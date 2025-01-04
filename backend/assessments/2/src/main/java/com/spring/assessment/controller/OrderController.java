package com.spring.assessment.controller;

import com.spring.assessment.entity.CartProduct;
import com.spring.assessment.entity.Order;
import com.spring.assessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return ResponseEntity.ok("Order placed successfully");
    }
}
