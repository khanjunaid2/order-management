package org.example.service.kafka.consumer;

import org.example.entity.CustomerOrder;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl {

    public OrderService orderService;

    @Autowired
    public ConsumerServiceImpl(OrderService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "${kafka.topic.order.groupId}")
    public void consumeCustomerData(@Header(KafkaHeaders.OFFSET) Long offset,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                                    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                    CustomerOrder customerOrder) {
        orderService.placeOrder(customerOrder);
    }

}
