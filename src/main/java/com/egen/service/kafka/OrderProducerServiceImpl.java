package com.egen.service.kafka;

import com.egen.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class OrderProducerServiceImpl {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    private final KafkaTemplate<String, OrderDTO> customerKafkaTemplate;

    @Value("${kafka.topic.order.name}")
    private String STRING_TOPIC;

    @Value("${kafka.topic.order.name}")
    private String JSON_TOPIC;

    public OrderProducerServiceImpl(KafkaTemplate<String, OrderDTO> kafkaTemplate, KafkaTemplate<String, OrderDTO> customerKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerKafkaTemplate = customerKafkaTemplate;
    }

    /**
     * To produce Order data.
     * @param orderDTO
     *
     * @return*/
    public boolean sendOrder(OrderDTO orderDTO) {
        log.info(String.format("Producing message: %s", orderDTO));

        customerKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrderDTO>> future = t.send(JSON_TOPIC, orderDTO);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDTO>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Unable to process messsage due to "+ throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrderDTO> result) {
                    RecordMetadata sentOrder = result.getRecordMetadata();
                    log.info(String.format("=>{}", sentOrder.offset(), "{}", sentOrder.topic()));
                }
            });
            return true;
        });
        return true;
    }
}
