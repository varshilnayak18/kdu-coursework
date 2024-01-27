package com.example.assignment2.exceptions.custom;

/**
 * Custom exception class representing an invalid request parameter in application
 * Exception is thrown when there is an issue with the provided request parameters
 */
public class InvalidRequestParameterException extends RuntimeException{
    public InvalidRequestParameterException(String msg){
        super(msg);
    }
}
