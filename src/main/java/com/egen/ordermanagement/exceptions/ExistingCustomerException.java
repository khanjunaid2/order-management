package com.egen.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.ALREADY_REPORTED)
public class ExistingCustomerException extends RuntimeException{
    public ExistingCustomerException(String message){
        super(message);
    }
}
