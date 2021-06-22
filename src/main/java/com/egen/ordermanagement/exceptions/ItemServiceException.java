package com.egen.ordermanagement.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemServiceException extends  RuntimeException {
    public static final long serialVersionUID = 1L;

    public ItemServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
