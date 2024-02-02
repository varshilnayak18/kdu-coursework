package com.spring.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    long cartProductId;
    @ManyToOne(fetch = FetchType.EAGER)
    Cart cart;
    @ManyToOne(fetch = FetchType.EAGER)
    ProductInventory productInventory;
    long productQuantity;
}
