package com.egen.service.impl;


import com.egen.model.GroceryOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import java.util.UUID;

@Service
public class ProducerService {

  private final KafkaTemplate<String, GroceryOrder> orderKafkaTemplate;

  @Value("$kafka.topic.order.name")
  private String ORDER_TOPIC;

  public ProducerService(KafkaTemplate<String, GroceryOrder> orderKafkaTemplate) {
    this.orderKafkaTemplate = orderKafkaTemplate;
  }

  public void sendOrderData(GroceryOrder groceryOrder) {

    orderKafkaTemplate.executeInTransaction(t -> {
      ListenableFuture<SendResult<String, GroceryOrder>> future = t
          .send(ORDER_TOPIC, groceryOrder.getCustomerId() + UUID.randomUUID(), groceryOrder);
      return true;
    });
  }

}