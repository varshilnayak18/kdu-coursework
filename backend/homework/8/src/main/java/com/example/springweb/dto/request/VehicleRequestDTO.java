package com.example.springweb.dto.request;

import com.example.springweb.constants.Brands;
import com.example.springweb.entities.Speaker;
import com.example.springweb.entities.Tyre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleRequestDTO {
    private int id;
    private Brands carBrandName;
    private Speaker speaker;
    private Tyre tyre;
    private double price;
}
