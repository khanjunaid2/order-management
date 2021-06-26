package com.egen.ordermanagement.exception;

public class BatchOrderServiceException extends RuntimeException {

    public BatchOrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BatchOrderServiceException(String message) {
        super(message);
    }
}
