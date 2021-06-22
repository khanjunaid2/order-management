package com.egen.ordermanagement.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public class OrderServiceException extends RuntimeException{
    public static final long serialVersionUID = 1L;
    public OrderServiceException(String message) {
        super(message);
    }
    public OrderServiceException(String msg, Throwable cause){
        super(msg,cause);
    }
}
