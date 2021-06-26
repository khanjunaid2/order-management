package org.example.service.kafka.producer;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.entity.CustomerOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerServiceImpl {

    private final KafkaTemplate<String, CustomerOrder> orderKafkaTemplate;

    @Value("${kafka.topic.order.name}")
    private String ORDER_TOPIC;

    public ProducerServiceImpl(KafkaTemplate<String, CustomerOrder> kafkaTemplate, KafkaTemplate<String, CustomerOrder> customerKafkaTemplate) {
        this.orderKafkaTemplate = customerKafkaTemplate;
    }

    public boolean sendOrder(CustomerOrder customerOrder) {

        orderKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, CustomerOrder>> future = t.send(ORDER_TOPIC, customerOrder);
            future.addCallback(new ListenableFutureCallback<SendResult<String, CustomerOrder>>() {
                @Override
                public void onFailure(Throwable throwable) {

                }

                @Override
                public void onSuccess(SendResult<String, CustomerOrder> stringCustomerOrderSendResult) {
                    RecordMetadata sentOrder = stringCustomerOrderSendResult.getRecordMetadata();
                }
            });
            return true;
        });
        return true;
    }

}
