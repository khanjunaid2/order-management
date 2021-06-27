package com.egen.service;

import com.egen.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

public class KafkaConsumerService {

    private OrderService orderService;

    public KafkaConsumerService(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "${kafka.topic.order.groupId}")
    public void consumeOrderData(@Header(KafkaHeaders.OFFSET) Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                    Order order) {
        orderService.placeOrder(order);
    }
}
