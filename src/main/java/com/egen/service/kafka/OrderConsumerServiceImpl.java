package com.egen.service.kafka;

import com.egen.dto.OrderDto;
import com.egen.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumerServiceImpl {

    public OrderService orderService;

    @Autowired
    public OrderConsumerServiceImpl(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "orderDtoKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "${kafka.topic.order.groupId}")
    public void consumeOrdersData(@Header(KafkaHeaders.OFFSET) long offset,
                                @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                OrderDto orderDTO) {
        log.info("Order {} consumed from partition {} at offset {}", key, partition, offset);
        orderService.placeOrder(orderDTO);
    }
}


