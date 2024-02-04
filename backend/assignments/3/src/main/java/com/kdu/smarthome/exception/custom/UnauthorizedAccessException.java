package com.kdu.smarthome.exception.custom;

/**
 * Custom exception indicating an unauthorized access attempt.
 */
public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
