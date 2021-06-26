package com.egen.ordermanagment.exception;

public class KafkaOrderException extends RuntimeException{
    public KafkaOrderException(String message) {
        super(message);
    }
}
