package com.example.ordermanagement.service.kakfa;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.service.orders.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    OrderService orderService;

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.name}",
            groupId = "${kafka.topic.groupId}")
    public void consumeOrdersDtoData(OrdersDto ordersDto,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID)String partitionId,
                                     @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String key,
                                     @Header(KafkaHeaders.RECEIVED_TOPIC)String topicName,
                                     @Header(KafkaHeaders.OFFSET)String offsetValue
                                     ){
        log.info("Consumed Message: Order Id: {}, Customer Name: {} from Partition ID: {} with Key : {} , topic : {} , offset : {}",
                ordersDto.getOrderId(), ordersDto.getCustomer().getFirstName(),
                partitionId, key, topicName, offsetValue);
        orderService.placeOrder(ordersDto);
    }

}
