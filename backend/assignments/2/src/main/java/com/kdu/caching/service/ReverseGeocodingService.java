package com.kdu.caching.service;

import com.kdu.caching.dto.AddressDTO;
import com.kdu.caching.exceptions.custom.InvalidRequestParameterException;
import com.kdu.caching.utils.ExternalApi;
import com.kdu.caching.utils.Mapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for reverse geocoding operations
 * Interacts with an external API to retrieve address based on given latitude and longitude coordinates
 * Caching functionality is also there to improve performance
 */
@Slf4j
@Service
public class ReverseGeocodingService {
    /**
     * Retrieves address based on the provided latitude and longitude coordinates and caches it to improve performance
     * @param latitude latitude coordinate for which the address needs to be obtained
     * @param longitude longitude coordinate for which the address needs to be obtained
     * @return AddressDTO containing the address information
     * @throws InvalidRequestParameterException if the provided coordinates are invalid
     */
    @Cacheable(key = "{#latitude,#longitude}", value = "reverse-geocoding")
    public AddressDTO getAddress(double latitude, double longitude){
        JsonNode jsonNode = ExternalApi.getJSON(latitude,longitude);
        if(jsonNode.get("data").isEmpty()){
            log.info("error");
            throw new InvalidRequestParameterException("Invalid parameter sent please check again\n");
        }
        AddressDTO addressDTO = Mapper.getAddressDTO(jsonNode);
        log.info("Fetched address from external API\n");
        return addressDTO;
    }
}
