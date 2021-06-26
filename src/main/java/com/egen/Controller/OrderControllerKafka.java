package com.egen.Controller;

import com.egen.DTO.OrderDTO;
import com.egen.Services.kafka.ProducerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderControllerKafka {
    ProducerServices producerServices;

    public OrderControllerKafka(ProducerServices producerServices) {
        this.producerServices = producerServices;
    }

    @PostMapping(value="/publish")
    public String sendMessageToKafkaTopic(@RequestBody OrderDTO order){
        log.info("Order Received: {}",order);
        producerServices.sendOrderData(order);
        return "Order Received";
    }
}