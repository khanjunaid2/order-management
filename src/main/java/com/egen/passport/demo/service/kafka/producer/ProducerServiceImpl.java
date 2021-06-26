package com.egen.passport.demo.service.kafka.producer;

import com.egen.passport.demo.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.network.Send;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class ProducerServiceImpl {

    private final KafkaTemplate<String, OrderDTO> orderKafkaTemplate;

    @Value("${kafka.topic.name}")
    private String ORDER_TOPIC;

    public ProducerServiceImpl(KafkaTemplate<String, OrderDTO> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    /**
     * To produce Customer Order.
     *
     * @param order
     */
    public boolean sendOrderData(OrderDTO order) {
        log.info(String.format("$$$$ => Producing message: %s", order));

        orderKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrderDTO>> future = t.send(ORDER_TOPIC, order.getCustomerId() + UUID.randomUUID(), order);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDTO>>() {

                @Override
                public void onSuccess(SendResult<String, OrderDTO> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ]", order, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", order, ex.getMessage());
                }
            });
            return true;
        });
        return true;
    }
}
