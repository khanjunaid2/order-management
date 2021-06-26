package com.egen.Services.kafka;

import com.egen.DTO.OrderDTO;
import com.egen.Services.OrderServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServices {

    public OrderServices orderServices;

    @Autowired
    public ConsumerServices(OrderServices orderServices){
        this.orderServices = orderServices;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.name}",
            groupId = "${kafka.topic.groupId}")
    public void consumeCustomerData(@Header(KafkaHeaders.OFFSET) Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                    OrderDTO order){
        log.info("Consumed order: {} for customerId {} from Partition {} at offset {}", key, order.getOrder_customer_id(), partition, offset);
        orderServices.addOrder(order);
    }
}
