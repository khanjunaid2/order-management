package com.egen.passport.demo.service.kafka;

import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    public OrderService orderService;

    @Autowired
    public ConsumerService(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.name}",
            groupId = "${kafka.topic.groupId}")
    public void consumeCustomerData(@Header(KafkaHeaders.OFFSET) Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                    OrderDTO order){
        log.info("Consumed order: {} for customerId {} from Partition {} at offset {}", key, order.getCustomerId(), partition, offset);
        orderService.createOrder(order);
    }
}
