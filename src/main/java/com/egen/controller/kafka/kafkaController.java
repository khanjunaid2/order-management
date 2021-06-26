package com.egen.controller.kafka;

import com.egen.dtos.ordersDTO;
import com.egen.exceptions.orderNotFoundException;
import com.egen.service.kafka.producer.producerKafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
class KafkaController {

    private final producerKafkaService producerService;

    public KafkaController(producerKafkaService producerService){
        this.producerService = producerService;
    }

    @PostMapping(value = "/kafka/publish/order")
    public String sendOrdertoKafkaTopic(@RequestBody ordersDTO orderDTO){
        try {
            System.out.println("Inside the Kafka Controller");
            String orderId = orderDTO.getId();
            orderDTO.setId(orderId.concat(Long.toString(Instant.now().getEpochSecond())));

            producerService.sendOrderItemData(orderDTO);
        }
        catch (Exception ex){
            throw new orderNotFoundException("Error while placing the order");
        }
        return "Order Received";
    }


}
