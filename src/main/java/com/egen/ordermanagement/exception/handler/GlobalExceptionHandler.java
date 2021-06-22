package com.egen.ordermanagement.exception.handler;

import com.egen.ordermanagement.exception.OrderServiceException;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderServiceException.class)
    public ResponseEntity<Response<?>> handleEmployeesServiceException(OrderServiceException e) {
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status) {
        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }
}
