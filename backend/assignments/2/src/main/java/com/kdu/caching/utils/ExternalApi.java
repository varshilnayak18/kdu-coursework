package com.kdu.caching.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Utility class for interacting with an external API to fetch geocoding and reverse geocoding data
 */
@Service
public class ExternalApi {
    private static final WebClient.Builder builder = WebClient.builder();

    private ExternalApi(){

    }

    /**
     * Retrieves JSON data from external API based on the provided address
     * @param address address for which coordinates need to be obtained
     * @return JsonNode containing the geocoding information
     */

    public static JsonNode getJSON(String address){
        return builder.build().get().uri(Mapper.getGeocodingURL(address)).retrieve().bodyToMono(JsonNode.class).block();
    }

    /**
     * Retrieves JSON data from external API based on the provided latitude and longitude
     * @param latitude latitude coordinate for which the address needs to be obtained
     * @param longitude longitude coordinate for which the address needs to be obtained
     * @return JsonNode containing the reverse geocoding information
     */
    public static JsonNode getJSON(double latitude, double longitude){
        return builder.build().get().uri(Mapper.getReverseGeocodingURL(latitude,longitude)).retrieve().bodyToMono(JsonNode.class).block();
    }
}
