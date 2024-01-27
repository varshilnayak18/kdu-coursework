package com.kdu.caching.utils;

import com.kdu.caching.constants.URLs;
import com.kdu.caching.dto.AddressDTO;
import com.kdu.caching.dto.CoordinatesDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Utility class providing mapping functions for converting JSON responses to DTOs and constructing API URLs
 */
public class Mapper {
    private static final String GEOCODING_URL = URLs.GEOCODING_URL.getValue();
    private static final String REVERSE_GEOCODING_URL = URLs.REVERSE_GEOCODING_URL.getValue();
    private static final String API_KEY = URLs.API_KEY.getValue();

    private Mapper(){

    }

    /**
     * Converts JsonNode containing geocoding information to CoordinatesDTO
     * @param jsonNode JsonNode containing geocoding information
     * @return CoordinatesDTO object representing latitude, longitude, and region
     */
    public static CoordinatesDTO getCoordinatesDTO(JsonNode jsonNode){
        JsonNode jsonNode1 = jsonNode.get("data").get(0);
        return new CoordinatesDTO(jsonNode1.get("latitude").asDouble(),jsonNode1.get("longitude").asDouble(),jsonNode1.get("region").asText());
    }

    /**
     * Converts JsonNode containing reverse geocoding information to AddressDTO
     * @param jsonNode JsonNode containing reverse geocoding information
     * @return AddressDTO object representing address information
     */
    public static AddressDTO getAddressDTO(JsonNode jsonNode){
        JsonNode jsonNode1 = jsonNode.get("data").get(0);
        StringBuilder addressStringBuilder = new StringBuilder();

        appendIfNotNull(addressStringBuilder, jsonNode1.get("name"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("street"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("locality"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("postal_code"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("region"));
        appendIfNotNull(addressStringBuilder, jsonNode1.get("country"));

        return new AddressDTO(addressStringBuilder.toString());
    }
    private static void appendIfNotNull(StringBuilder builder, JsonNode valueNode) {
        if (valueNode != null && !valueNode.isNull()) {
            String value = valueNode.asText();
            if (value != null && !value.equalsIgnoreCase("null")) {
                if (!builder.isEmpty()) {
                    builder.append(", ");
                }
                builder.append(value);
            }
        }
    }

    /**
     * Constructs geocoding API URL based on the provided address
     * @param address address for which the geocoding URL needs to be constructed
     * @return geocoding API URL
     */
    public static String getGeocodingURL(String address){
        return UriComponentsBuilder.fromUriString(GEOCODING_URL).
                queryParam("access_key",API_KEY).queryParam("query",address).build().toUriString();
    }

    /**
     * Constructs reverse geocoding API URL based on the provided latitude and longitude
     * @param latitude latitude coordinate for which the reverse geocoding URL needs to be constructed
     * @param longitude longitude coordinate for which the reverse geocoding URL needs to be constructed
     * @return reverse geocoding API URL
     */
    public static String getReverseGeocodingURL(double latitude, double longitude){
        String query = latitude + "," + longitude;
        return UriComponentsBuilder.fromUriString(REVERSE_GEOCODING_URL).
                queryParam("access_key",API_KEY).queryParam("query",query).build().toUriString();
    }
}
