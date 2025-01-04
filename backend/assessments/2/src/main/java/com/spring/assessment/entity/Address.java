package com.spring.assessment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long addressId;
    String aliasName;
    String street;
    String city;
    String state;
    long postalCode;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;
}
