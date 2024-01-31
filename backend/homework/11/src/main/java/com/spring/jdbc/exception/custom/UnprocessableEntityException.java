package com.spring.jdbc.exception.custom;

public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String msg){
        super(msg);
    }
}
