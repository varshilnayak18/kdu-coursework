package com.spring.assessment.controller;

import com.spring.assessment.entity.Address;
import com.spring.assessment.entity.Cart;
import com.spring.assessment.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<String> addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        return ResponseEntity.ok("Cart added successfully");
    }
}
