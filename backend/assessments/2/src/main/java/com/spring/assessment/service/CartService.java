package com.spring.assessment.service;

import com.spring.assessment.entity.Cart;
import com.spring.assessment.exception.custom.UnprocessableEntityException;
import com.spring.assessment.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartRepository cartRepository;
    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addCart(Cart cart) {
        try {
            cartRepository.save(cart);
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Cart cannot be added please check the entity again.");
        }
    }
}
