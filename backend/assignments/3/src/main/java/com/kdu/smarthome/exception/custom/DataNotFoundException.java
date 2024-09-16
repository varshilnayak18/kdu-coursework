package com.kdu.smarthome.exception.custom;

/**
 * Custom exception indicating that requested data was not found.
 */
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String msg){
        super(msg);
    }
}

