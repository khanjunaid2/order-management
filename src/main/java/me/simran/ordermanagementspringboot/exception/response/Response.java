package me.simran.ordermanagementspringboot.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
@Data
@Builder
@RequiredArgsConstructor(staticName = "from")
public class Response<T> {

    @NonNull ResponseMetadata meta;
    @JsonInclude(NON_NULL)
    T data;

    public Response(ResponseMetadata meta, T data){
        this.meta = meta;
        this.data = data;
    }
}
