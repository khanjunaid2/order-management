package org.example.exception.handler;

import org.example.exception.BadRequestException;
import org.example.exception.InternalServerException;
import org.example.exception.OrderServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "BadRequest Occurred")
    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(){
        System.out.println("BadRequest Exception handler executed");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error Occurred")
    @ExceptionHandler(InternalServerException.class)
    public void handleInternalServerException(){
        System.out.println("InternalServer Exception handler executed");
    }

    @ExceptionHandler(OrderServiceException.class)
    public void handleOrderServiceException(){
        System.out.println("OrderService Exception handler executed");
    }

}
