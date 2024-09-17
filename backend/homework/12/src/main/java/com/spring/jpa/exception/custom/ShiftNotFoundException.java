package com.spring.jpa.exception.custom;

public class ShiftNotFoundException extends RuntimeException{
    public ShiftNotFoundException(String msg) {
        super(msg);
    }
}
