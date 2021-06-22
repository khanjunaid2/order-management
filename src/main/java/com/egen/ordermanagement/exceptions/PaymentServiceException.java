package com.egen.ordermanagement.exceptions;

public class PaymentServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public PaymentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
