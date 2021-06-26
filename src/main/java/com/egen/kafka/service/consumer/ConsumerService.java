package com.egen.kafka.service.consumer;


import com.egen.model.Order;
import com.egen.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    OrderService orderService;

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json-demo.name}",
            groupId = "${kafka.topic.json-demo.groupId}")
    public void consumeOrderData(Order order) {
        log.info("Consumed Message: {}, {}", order.getId(), order.getCustomer().getEmail());
        orderService.placeOrder(order);
    }
}
