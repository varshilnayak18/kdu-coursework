package com.kdu.caching.controller;

import com.kdu.caching.dto.CoordinatesDTO;
import com.kdu.caching.service.GeocodingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling geocoding related requests
 * Provides an endpoint "/geocoding" for obtaining coordinates based on a given address
 */
@Slf4j
@RestController
@RequestMapping("/geocoding")
public class GeocodingController {
    private final GeocodingService geocodingService;

    /**
     * Constructor injection
     * @param geocodingService instance of GeocodingService to perform geocoding operations
     */
    @Autowired
    public GeocodingController(GeocodingService geocodingService){
        this.geocodingService = geocodingService;
    }

    /**
     * Endpoint for getting coordinates based on the provided address
     * @param address address for which coordinates need to be obtained
     * @return CoordinatesDTO containing latitude and longitude information
     */
    @GetMapping
    public CoordinatesDTO getCoordinates(@RequestParam String address){
        CoordinatesDTO coordinatesDTO = geocodingService.getCoordinates(address);
        log.info("The co-ordinates corresponding to " + address + " are: " + coordinatesDTO.print() + "\n");
        return coordinatesDTO;
    }
}
