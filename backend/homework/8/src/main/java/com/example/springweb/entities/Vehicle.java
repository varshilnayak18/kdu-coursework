package com.example.springweb.entities;

import com.example.springweb.constants.Brands;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private int id;
    private Brands carBrandName;
    private Speaker speaker;
    private Tyre tyre;
    private double price;
}
