package com.example.springweb.dto.response;

import com.example.springweb.constants.Brands;
import com.example.springweb.entities.Speaker;
import com.example.springweb.entities.Tyre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponseDTO {
    private int id;
    private Brands carBrandName;
    private Speaker speaker;
    private Tyre tyre;
    private double price;
    private String response;

    public VehicleResponseDTO(String response){
        this.response = response;
    }
}
