package com.egen.controller;


import com.egen.dto.OrderItemDTO;
import com.egen.exception.OrderServiceException;
import com.egen.service.kafka.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class KafkaController {

    private final ProducerService producerService;

    public KafkaController(ProducerService producerService){
        this.producerService = producerService;
    }

    @PostMapping(value = "/kafka/publish/order")
    public String sendOrdertoKafkaTopic(@RequestBody OrderItemDTO orderItemDTO){
        try {
            System.out.println("Inside the Kafka Controller");
            String orderId = orderItemDTO.getOrderId();
            orderItemDTO.setOrderId(orderId.concat(Long.toString(Instant.now().getEpochSecond())));

            producerService.sendOrderItemData(orderItemDTO);
        }
        catch (Exception ex){
            throw new OrderServiceException("Error while placing the order");
        }
        return "Order Received";
    }


}
