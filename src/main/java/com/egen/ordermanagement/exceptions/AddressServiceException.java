package com.egen.ordermanagement.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressServiceException extends RuntimeException{
    public static final long serialVersionUID = 1L;
    public AddressServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressServiceException(String message) {
        super(message);
    }
}
