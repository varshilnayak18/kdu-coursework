package com.kdu.caching.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing an error response
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private int statusCode;
}
