package com.egen.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.*;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor(staticName = "from")
public class ResponseMetadata {

    int statusCode;
    @NonNull
    String statusMessage;
    @JsonInclude(NON_EMPTY)
    Map<String, Object> tags;

    public ResponseMetadata(int statusCode , String statusMessage, Map<String, Object> tags){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.tags = tags;
    }

}