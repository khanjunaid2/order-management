package com.egen.service.kafka.consumer;

import com.egen.dtos.ordersDTO;
import com.egen.service.IOrdersService;
import com.egen.service.impl.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class consumerKafkaService {
    public OrdersService orderService;

    @Autowired
    public consumerKafkaService(OrdersService orderService){
        this.orderService = orderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.order.name}",
            groupId = "${kafka.topic.order.groupId}")
    public void consumeCustomerData(ordersDTO orderDTO) {
        log.info("Consumed Message: {}, {}", orderDTO.getPaymentDetails(), orderDTO.getId());
        orderService.createOrder(orderDTO);
    }


}
