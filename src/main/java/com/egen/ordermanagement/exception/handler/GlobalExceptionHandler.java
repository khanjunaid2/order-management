package com.egen.ordermanagement.exception.handler;


import com.egen.ordermanagement.exception.BatchOrderServiceException;
import com.egen.ordermanagement.exception.OrderRequestProcessException;
import com.egen.ordermanagement.exception.OrderServiceException;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(OrderRequestProcessException.class)
    public ResponseEntity<Response<?>> handleOrderRequestException(OrderRequestProcessException e) {

        log.error(e.getClass().getSimpleName(), e.getMessage());
        return buildResponse(StatusMessage.REQUEST_NOT_PROCESSED, BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<Response<?>> handleOrderServiceException(Exception e) {

        logger(e.getClass().getSimpleName(), e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR, "Unable to process the request");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response<?>> handleMissingParams(MissingServletRequestParameterException e) {

        String errorResponseMessage = e.getParameterName() + " request parameter is missing";
        log.error(e.getClass().getSimpleName(), errorResponseMessage);
        return buildResponse(StatusMessage.MISSING_REQUEST_PARAMETER, BAD_REQUEST, errorResponseMessage);
    }

    @ExceptionHandler(BatchOrderServiceException.class)
    public ResponseEntity<Void> handleMissingParams(BatchOrderServiceException e) {

        log.error(e.getClass().getSimpleName(), e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status, String errors)  {

        Map<String, Object> errorsMap = new HashMap<>();

        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .data(errors)
                .build();
        return ResponseEntity.status(status)
                             .body(response);
    }

    protected void logger(String exception, String message) {

        log.error("{} exception thrown from Order Service Controller: {}",exception, message);
    }
}