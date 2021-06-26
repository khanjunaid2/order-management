package com.egen.ordermanagement.service.kafka.consumer;


import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.*;

@Service
@Slf4j
public class ConsumeServiceImpl {

    public OrdersService ordersService;

    @Autowired
    public ConsumeServiceImpl(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Collects the data from producer to place the order
     * @param offset
     * @param partition
     * @param key
     * @param orderDto
     */
    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "{kafka.topic.order.groupId}")
    public void consumeOrderDetails(@Header(KafkaHeaders.OFFSET)Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID)Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String key,
                                    OrderDto orderDto){
        log.info("Consumed order: {} for customerID: {} from Partition: {} at Offset: {}",key
                ,orderDto.getCustomerId(),partition,offset);
        ordersService.createOrder(orderDto);
    }
}
