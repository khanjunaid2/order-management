package com.egen.service;

import com.egen.model.Order;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaProducerService {

    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    @Value("${kafka.topic.name}")
    private String ORDER_TOPIC;

    public KafkaProducerService(KafkaTemplate<String, Order> kafkaTemplate, KafkaTemplate<String, Order> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public boolean sendOrder(Order order) {

        orderKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, Order>> future = t.send(ORDER_TOPIC, order);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(SendResult<String, Order> stringCustomerOrderSendResult) {
                    RecordMetadata sentOrder = stringCustomerOrderSendResult.getRecordMetadata();
                }
            });
            return true;
        });
        return true;
    }
}
