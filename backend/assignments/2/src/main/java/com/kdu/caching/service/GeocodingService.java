package com.kdu.caching.service;

import com.kdu.caching.dto.CoordinatesDTO;
import com.kdu.caching.exceptions.custom.InvalidRequestParameterException;
import com.kdu.caching.utils.Mapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.kdu.caching.utils.ExternalApi;

/**
 * Service class responsible for geocoding operations
 * Interacts with an external API to retrieve coordinates based on a given address
 * Caching functionality is also there to improve performance
 */
@Slf4j
@Service
public class GeocodingService {
    /**
     * Retrieves coordinates based on the provided address and caches it to improve performance
     * Caching is ignored if the address corresponds to the "Goa"
     * @param address address for which coordinates need to be obtained
     * @return CoordinatesDTO containing latitude and longitude information
     * @throws InvalidRequestParameterException if the provided address is invalid
     */
    @Cacheable(key = "#address", value = "geocoding", unless = "#result.getRegion().equalsIgnoreCase(\"Goa\")")
    public CoordinatesDTO getCoordinates(String address){
        JsonNode jsonNode = ExternalApi.getJSON(address);
        if(jsonNode.get("data").isEmpty()){
            log.error("Failed to fetch co-ordinates due to invalid address\n");
            throw new InvalidRequestParameterException("There are no co-ordinates available for " + address + " please check the address again");
        }
        CoordinatesDTO coordinatesDTO = Mapper.getCoordinatesDTO(jsonNode);
        log.info("Fetched co-ordinates from external API\n");
        return coordinatesDTO;
    }
}
