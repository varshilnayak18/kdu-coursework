package com.example.assignment2.controller;

import com.example.assignment2.dto.AddressDTO;
import com.example.assignment2.service.ReverseGeocodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling reverse geocoding-related requests
 * Provides an endpoint "/reverse-geocoding" for obtaining address based on given latitude and longitude coordinates
 */
@Slf4j
@RestController
@RequestMapping("/reverse-geocoding")
public class ReverseGeocodingController {
    private final ReverseGeocodingService reverseGeocodingService;

    /**
     * Constructor injection
     * @param reverseGeocodingService instance of ReverseGeocodingService to perform reverse geocoding operations
     */
    @Autowired
    public ReverseGeocodingController(ReverseGeocodingService reverseGeocodingService){
        this.reverseGeocodingService = reverseGeocodingService;
    }

    /**
     * Endpoint for getting address based on the provided latitude and longitude coordinates
     * @param latitude latitude coordinate for which the address needs to be obtained
     * @param longitude longitude coordinate for which the address needs to be obtained
     * @return String representing the address corresponding to the provided coordinates
     */
    @GetMapping
    public String getAddress(@RequestParam double latitude, @RequestParam double longitude){
        AddressDTO addressDTO = reverseGeocodingService.getAddress(latitude,longitude);
        log.info("The address corresponding to [latitude: " + latitude + ", longitude: " + longitude + "] is: " + addressDTO.getAddress() + "\n");
        return addressDTO.getAddress();
    }
}
