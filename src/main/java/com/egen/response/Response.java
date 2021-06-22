package com.egen.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@RequiredArgsConstructor(staticName = "from")
public class Response<T> {
    @NotNull
    ResponseMetadata meta;

    @JsonProperty("data")
    @JsonInclude(NON_NULL)
    T data;

    public Response(ResponseMetadata meta , T data){
        this.meta = meta;
        this.data = data;
    }
}
