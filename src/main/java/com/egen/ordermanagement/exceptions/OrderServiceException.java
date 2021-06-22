package com.egen.ordermanagement.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RequiredArgsConstructor
public class OrderServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;
    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderServiceException(String message) {
        super(message);
    }
}