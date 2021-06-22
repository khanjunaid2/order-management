package com.egen.exception;

public class ParameterMissingException extends RuntimeException{
    public ParameterMissingException(String message){
        super(message);
    }
}
