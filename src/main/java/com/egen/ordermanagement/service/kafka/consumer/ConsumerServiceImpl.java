package com.egen.ordermanagement.service.kafka.consumer;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.model.Order;
import com.egen.ordermanagement.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.*;

@Service
@Slf4j
public class ConsumerServiceImpl {

    public OrderService orderService;

    public ConsumerServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "{kafka.topic.order.groupId}")
    public void consumeOrderDetails(@Header(KafkaHeaders.OFFSET)Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID)Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String key,
                                    OrderDTO orderDTO){
        log.info("Consumed order: {} for customerID: {} from Partition: {} at Offset: {}",key
                ,orderDTO.getCustomerId(),partition,offset);
        orderService.createOrder(orderDTO);
    }
}
