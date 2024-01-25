package com.example.springweb.exceptions.custom;

/**
 * class for handling exceptions where the id or any attribute of vehicle
 * is not existing
 */
public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String msg){
        super(msg);
    }
}
