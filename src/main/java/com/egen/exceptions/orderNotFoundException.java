package com.egen.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class orderNotFoundException extends RuntimeException{
    public orderNotFoundException(String message){
        super(message);
    }

}
