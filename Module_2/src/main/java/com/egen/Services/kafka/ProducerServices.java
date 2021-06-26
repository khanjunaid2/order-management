package com.egen.Services.kafka;

import com.egen.DTO.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@Slf4j
public class ProducerServices {
    private final KafkaTemplate<String, OrderDTO> orderKafkaTemplate;

    @Value("$kafka.topic.name")
    private String ORDER_TOPIC;

    public ProducerServices(KafkaTemplate<String, OrderDTO> orderKafkaTemplate){
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public void sendOrderData(OrderDTO order){
        log.info(String.format("$$$ => Producing Message: %s",order));

        orderKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String,OrderDTO>> future = t.send(ORDER_TOPIC, order.getOrderId() + UUID.randomUUID(), order);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDTO>>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[{}] due to {}",order,ex.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrderDTO> result) {
                    log.info("Sent Message=[{}] with offset=[{}]",order,result.getRecordMetadata().offset());
                }
            });
            return true;
        });
    }
}
