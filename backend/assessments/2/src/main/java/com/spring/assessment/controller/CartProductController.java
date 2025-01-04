package com.spring.assessment.controller;

import com.spring.assessment.entity.Address;
import com.spring.assessment.entity.CartProduct;
import com.spring.assessment.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-product")
public class CartProductController {
    private CartProductService cartProductService;
    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @PostMapping
    public ResponseEntity<String> addCartProduct(@RequestBody CartProduct cartProduct){
        cartProductService.addCartProduct(cartProduct);
        return ResponseEntity.ok("Product added to Cart successfully");
    }

    @GetMapping
    public ResponseEntity<List<CartProduct>> getProducts(@RequestParam long userId){
        return ResponseEntity.ok(cartProductService.getProducts(userId));
    }
}
