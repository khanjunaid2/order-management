package com.egen.ordermanagement.service.kafka;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.dto.OrderStatusDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String, JsonNode> orderServiceKafkaTemplate;

    @Value("${kafka.topic.name.orders}")
    private String batchOrderTopic;

    @Value("${kafka.topic.name.status}")
    private String batchOrderStatusTopic;

    public ProducerServiceImpl(KafkaTemplate<String, JsonNode> orderServiceKafkaTemplate) {

        this.orderServiceKafkaTemplate = orderServiceKafkaTemplate;
    }

    /**
     * produce received batch Orders to kafka cluster.
     * */
    public boolean sendBatchOrder(List<OrderDTO> orderPayload) {

        log.info("=> Produced order payload: {}", orderPayload.toString());

        orderPayload.forEach(order -> sendEachMessage(batchOrderTopic,
                                                      order.getOrderId(),
                                                      new ObjectMapper().convertValue(order, JsonNode.class)));
        return true;
    }

    /**
     * produce received batch Order status messages to kafka cluster.
     * */
    @Override
    public boolean updateOrderStatus(List<OrderStatusDTO> orderStatusDTO) {

        log.info("=> Produced order status payload: {}", orderStatusDTO.toString());

        orderStatusDTO.forEach(order -> sendEachMessage(batchOrderStatusTopic,
                order.getOrderId(),
                new ObjectMapper().convertValue(order, JsonNode.class)));
        return true;
    }

    /**
     * produce each message to kafka with a call back.
     */
    private void sendEachMessage(String topic, String key, JsonNode message) {

        orderServiceKafkaTemplate.executeInTransaction(transaction -> {

            ListenableFuture<SendResult<String, JsonNode>> future = transaction.send(topic, key, message);

            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                public void onSuccess(SendResult<String, JsonNode> result) {
                    RecordMetadata sentMessage = result.getRecordMetadata();
                    log.info("=> produced message {} {} {}", sentMessage.offset(), sentMessage.topic(), sentMessage.partition());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Failed to produce message=[ {} ] due to : {}", message, ex.getMessage());
                }
            });
            return true;
        });
    }
}
