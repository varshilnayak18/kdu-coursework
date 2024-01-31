package com.spring.jpa.exception.custom;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String msg){
        super(msg);
    }
}
