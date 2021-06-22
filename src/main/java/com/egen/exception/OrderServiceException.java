package com.egen.exception;

public class OrderServiceException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
