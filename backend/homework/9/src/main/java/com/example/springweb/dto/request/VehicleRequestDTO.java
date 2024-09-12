package com.example.springweb.dto.request;

import com.example.springweb.constants.Brands;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * DTO class for handling incoming request
 */
@Data
@AllArgsConstructor
public class VehicleRequestDTO {
    @NonNull
    private int id;
    @NonNull
    private Brands carBrandName;
    @NonNull
    private Brands speakerBrandName;
    @NonNull
    private Brands tyreBrandName;
    @NonNull
    private double price;
}
