package com.egen.service.impl;



import com.egen.model.GroceryOrder;
import com.egen.service.IOrderManagementService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.*;

@Service
public class ConsumerService {

  public IOrderManagementService orderService;

  public ConsumerService(IOrderManagementService orderService) {
    this.orderService = orderService;
  }

  @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
      topics = "${kafka.topic.order.name}",
      groupId = "{kafka.topic.order.groupId}")
  public void consumeOrderDetails(@Header(KafkaHeaders.OFFSET) Long offset,
      @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
      @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
      GroceryOrder orderDTO) {

    orderService.saveOrder(orderDTO);
  }
}