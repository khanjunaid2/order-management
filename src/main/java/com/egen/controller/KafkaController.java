package com.egen.controller;

import com.egen.dto.OrderDTO;
import com.egen.exception.OrderServiceException;
import com.egen.service.kafka.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class KafkaController {

    private final ProducerService producerService;

    public KafkaController(ProducerService producerService){
        this.producerService = producerService;
    }

    @PostMapping(value = "/kafka/publish/order")
    public String sendOrdertoKafkaTopic(@RequestBody OrderDTO orderDTO){
        try {
            System.out.println("Inside the Kafka Controller");
            String orderId = orderDTO.getId();
            orderDTO.setId(orderId.concat(Long.toString(Instant.now().getEpochSecond())));

            producerService.sendOrderItemData(orderDTO);
        }
        catch (Exception ex){
            throw new OrderServiceException("Error while placing the order",ex);
        }
        return "Order Received";
    }


}
