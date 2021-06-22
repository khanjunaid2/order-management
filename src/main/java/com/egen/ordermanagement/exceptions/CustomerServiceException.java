package com.egen.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomerServiceException  extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public CustomerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerServiceException(String message) {
        super(message);
    }
}
