package com.egen.service.kafka;

import com.egen.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@Slf4j
public class OrderProducerServiceImpl {

    private final KafkaTemplate<String, OrderDto> orderDtoKafkaTemplate;

    @Value("${kafka.topic.order.name}")
    private String JSON_TOPIC;

    public OrderProducerServiceImpl(KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.orderDtoKafkaTemplate = kafkaTemplate;
    }

    /**
     *
     * Produce messages into Kafka
     *
     */
    public boolean sendOrder(OrderDto orderDTO) {
        log.info(String.format("Producing Orders: %s", orderDTO));

        orderDtoKafkaTemplate.executeInTransaction(transaction -> {
            ListenableFuture<SendResult<String, OrderDto>> future = transaction.send(JSON_TOPIC, UUID.randomUUID().toString(), orderDTO);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDto>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Unable to process order {} due to ", orderDTO, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrderDto> result) {
                    RecordMetadata sentOrder = result.getRecordMetadata();
                    log.info(String.format("Produced order {} at offset {}", sentOrder.offset(), sentOrder.topic()));
                }
            });
            return true;
        });
        return true;
    }
}
