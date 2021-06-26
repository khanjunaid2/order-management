package com.egen.service.kafka;

import com.egen.dto.OrderItemDTO;
import com.egen.service.OrderService;
import com.egen.service.OrderServiceImplement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    private OrderServiceImplement orderServiceImplement;

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${spring.kafka.topic.ordermanagment.name}",       //spring.kafka.topic.ordermanagment.name= order-managment
//            spring.kafka.topic.ordermanagment.groupId= OrderManagmentGroup.
            groupId = "${spring.kafka.topic.ordermanagment.groupId}")
    public void consumeOrderItemData(OrderItemDTO orderItemDTO) {
        System.out.println("Inside the Consumer Service");
        log.info("Consumed Message: {}, {}", orderItemDTO.getOrderId(), orderItemDTO.getId());
//        EmailSender.sendEmail("chicago@egen.solutions", orderItemDTO.getEmail(), orderItemDTO.getMessage());
        orderServiceImplement.placeOrder(orderItemDTO);

    }

}
