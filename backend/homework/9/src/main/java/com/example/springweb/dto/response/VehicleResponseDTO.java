package com.example.springweb.dto.response;

import com.example.springweb.constants.Brands;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class for sending response object
 */
@Data
@AllArgsConstructor
public class VehicleResponseDTO {
    private int id;
    private Brands carBrandName;
    private double price;
}
