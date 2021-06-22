package com.egen.ordermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal Server Error occurred")
    @ExceptionHandler(InternalServerException.class)
    public void handleInternalServerException(){
        log.info("InternalServerException handler executed");
    }
}
