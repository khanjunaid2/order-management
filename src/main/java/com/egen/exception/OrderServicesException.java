package com.egen.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServicesException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public OrderServicesException(String message, Throwable cause) {
        super(message, cause);
    }
}