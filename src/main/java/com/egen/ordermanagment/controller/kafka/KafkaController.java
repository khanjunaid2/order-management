package com.egen.ordermanagment.controller.kafka;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.response.Response;
import com.egen.ordermanagment.response.ResponseMetadata;
import com.egen.ordermanagment.response.StatusMessage;
import com.egen.ordermanagment.services.kafka.producer.ProducerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/orders")
public class KafkaController {

    @Autowired
    private ProducerServiceImpl producerService;

    @PostMapping(value="/publish")
    @ApiOperation(value = "",
            notes = "Creates Order using raw json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Orders received from kafka"),
            @ApiResponse(code = 500, message = "Error while retrieving orders") })
    public Response<String> placeOrder(@RequestBody OrdersDTO order) {
        return producerService.sendOrdersData(order) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Orders received from kafka")
                .build()
                :Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(400)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Error while saving orders")
                .build();
    }
}
