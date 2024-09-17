package com.spring.jpa.exception.custom;

public class UnprocessableEntityException extends RuntimeException{
    public UnprocessableEntityException(String msg){
        super(msg);
    }
}
