package com.example.ordermanagement.controller;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.service.kakfa.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "orders")
public class KafkaOrderCreatedController {

    @Autowired
    ProducerService producerService;

    @PostMapping(value = "/publish/order")
    public String publishOrderCreated(@RequestBody OrdersDto orders){
        log.info("Order Message received in KafkaController: {}", orders);
        producerService.sendOrderCreatedMessage(orders);
        return "Order Placed";
    }
}
