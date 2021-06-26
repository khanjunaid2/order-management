package com.egen.ordermanagement.service.kafka;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.dto.OrderStatusDTO;
import com.egen.ordermanagement.exception.BatchOrderServiceException;
import com.egen.ordermanagement.service.order.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerServiceImpl {

    @Value("${kafka.topic.name.orders}")
    private String batchOrderTopic;

    @Value("${kafka.topic.name.status}")
    private String batchOrderStatusTopic;

    private final OrderService orderService;

    public ConsumerServiceImpl(OrderService orderService) {

        this.orderService = orderService;
    }


    /**
     * A listener that receives messages on a specified list of topics.
     */
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory",
                   topics = {"${kafka.topic.name.orders}", "${kafka.topic.name.status}"},
                   groupId = "${kafka.consumer.groupId}")
    public void orderServiceDataListener(@Payload JsonNode jsonNode,
                                         @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                         @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {

        log.info("Consumed Message: {} {} {} {}", jsonNode.toString(), key, partition, ts);

        try {
            if(topic.equals(batchOrderTopic))
                orderService.placeOrder(new ObjectMapper().convertValue(jsonNode, OrderDTO.class));

            else if(topic.equals(batchOrderStatusTopic))
                orderService.updateOrderStatus(new ObjectMapper().convertValue(jsonNode, OrderStatusDTO.class));

        } catch (Exception e) {
            throw new BatchOrderServiceException("Failed to process request", e);
        }
    }
}
