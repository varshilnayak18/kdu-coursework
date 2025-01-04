package com.spring.assessment.service;

import com.spring.assessment.entity.Cart;
import com.spring.assessment.entity.CartProduct;
import com.spring.assessment.exception.custom.UnprocessableEntityException;
import com.spring.assessment.repo.CartProductRepository;
import com.spring.assessment.repo.CartRepository;
import com.spring.assessment.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService {
    private CartProductRepository cartProductRepository;
    private CartRepository cartRepository;

    @Autowired
    public CartProductService(CartProductRepository cartProductRepository, CartRepository cartRepository) {
        this.cartProductRepository = cartProductRepository;
        this.cartRepository = cartRepository;
    }
    public void addCartProduct(CartProduct cartProduct){
        try {
            cartProductRepository.save(cartProduct);
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Product cannot be added to Cart please check the entity again.");
        }
    }

    public List<CartProduct> getProducts(long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return cartProductRepository.findByCartId(cart.getCartId());
    }

    public void updateProduct(long userId, long productId, long quantity){
        Cart cart = cartRepository.findByUserId(userId);
        cartProductRepository.updateProduct(cart.getCartId(),productId,quantity);
    }

    public void deleteProduct(long userId, long productId){
        Cart cart = cartRepository.findByUserId(userId);
        cartProductRepository.deleteProduct(cart.getCartId(),productId);
    }
}
