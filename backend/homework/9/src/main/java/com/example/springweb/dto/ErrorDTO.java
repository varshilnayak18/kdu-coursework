package com.example.springweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO class for sending error response object
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String msg;
    private int statusCode;
}
