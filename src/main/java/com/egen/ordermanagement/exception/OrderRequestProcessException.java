package com.egen.ordermanagement.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderRequestProcessException extends RuntimeException {

    public OrderRequestProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRequestProcessException(String message) {
        super(message);
    }
}
