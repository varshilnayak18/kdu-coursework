package com.spring.assessment.service;

import com.spring.assessment.entity.Cart;
import com.spring.assessment.entity.CartProduct;
import com.spring.assessment.entity.Order;
import com.spring.assessment.exception.custom.UnprocessableEntityException;
import com.spring.assessment.repo.CartProductRepository;
import com.spring.assessment.repo.CartRepository;
import com.spring.assessment.repo.OrderRepository;
import com.spring.assessment.repo.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CartProductRepository cartProductRepository;
    private CartRepository cartRepository;
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartProductRepository cartProductRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartProductRepository = cartProductRepository;
        this.cartRepository = cartRepository;
    }
    public void addOrder(Order order) {
        try {
            double amount = 0.0;
            List<CartProduct> cartProducts = cartProductRepository.findByCartId(order.getCart().getCartId());
            for(CartProduct p: cartProducts){
                amount += p.getProductInventory().getPrice();
            }
            order.setAmount(amount);
            orderRepository.save(order);
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Order cannot be placed please check the entity again.");
        }
    }
}
