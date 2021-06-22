package com.egen.ordermanagement.response;

import java.util.*;

public class ErrorResponse {

    List<String> errorMessages;
    Map<String, Object> errorTags;

    public ErrorResponse(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ErrorResponse(String errorMessages) {

        this.errorMessages = Collections.singletonList(errorMessages);
    }

    public Map<String, Object> getErrorTags(String tag, Object errorMessageObject) {

        errorTags = new HashMap<>();
        errorTags.put(tag, errorMessageObject);
        return  errorTags;
    }
}
