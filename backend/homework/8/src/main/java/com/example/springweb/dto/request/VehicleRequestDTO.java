package com.example.springweb.dto.request;

import com.example.springweb.constants.Brands;
import com.example.springweb.entities.Speaker;
import com.example.springweb.entities.Tyre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * DTO class for handling incoming request
 */
@Data
@AllArgsConstructor
public class VehicleRequestDTO {
    private int id;
    @NonNull
    private Brands carBrandName;
    @NonNull
    private Speaker speaker;
    @NonNull
    private Tyre tyre;
    private double price;
}
