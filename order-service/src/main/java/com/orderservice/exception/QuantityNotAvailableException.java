package com.orderservice.exception;

public class QuantityNotAvailableException extends RuntimeException{
    public QuantityNotAvailableException(String msg){
        super(msg);
    }
}
