package com.spring.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long orderId;
    Date orderDate;
    double amount;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;
    @OneToOne(fetch = FetchType.EAGER)
    Address address;
    @OneToOne(fetch = FetchType.EAGER)
    Cart cart;
}
