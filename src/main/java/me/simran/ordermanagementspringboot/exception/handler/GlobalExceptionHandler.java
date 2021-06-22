package me.simran.ordermanagementspringboot.exception.handler;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import me.simran.ordermanagementspringboot.exception.OrderServiceException;
import me.simran.ordermanagementspringboot.exception.response.Response;
import me.simran.ordermanagementspringboot.exception.response.ResponseMetadata;
import me.simran.ordermanagementspringboot.exception.response.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public ResponseEntity<Response<?>> handleOrderServiceException(OrderServiceException e){
        log.error(e.getMessage());
        return buildResponse(StatusMessage.UNKNOWN_INTERNAL_ERROR, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Response<?>> buildResponse(StatusMessage statusMessage, HttpStatus status){
        var response = Response.builder()
                .meta(ResponseMetadata.builder()
                        .statusMessage(statusMessage.name())
                        .statusCode(status.value())
                        .build())
                .build();
        return ResponseEntity.status(status).body(response);

    }
}
