package com.egen.ordermanagment.services.kafka.consumer;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl {

    public OrderService orderService;

    @Autowired
    public ConsumerServiceImpl(OrderService orderService){
        this.orderService = orderService;
    }

    /**
     * Gets order data from producer and place order
     * @param offset
     * @param partition
     * @param key
     * @param ordersDTO
     */
    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "${kafka.topic.order.groupId}")
    public void consumeCustomerData(@Header(KafkaHeaders.OFFSET)Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID)Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String key,
                                    OrdersDTO ordersDTO) {
        log.info("Consumed order: {} for customerId: {} from Partition: {} at Offset: {}",
                key,
                ordersDTO.getCustomerId(),
                partition,
                offset);
        orderService.placeOrder(ordersDTO);
    }
}
