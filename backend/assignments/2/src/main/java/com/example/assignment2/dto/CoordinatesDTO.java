package com.example.assignment2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing geographical coordinates
 * The 'region' field is marked with @JsonIgnore to exclude it from JSON
 */
@Data
@AllArgsConstructor
public class CoordinatesDTO {
    double latitude;
    double longitude;
    @JsonIgnore
    String region;

    public String print(){
        return "[latitude: " + latitude + ", longitude: " + longitude + "]";
    }
}
