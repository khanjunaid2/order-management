package com.egen.ordermanagement.service.kafka.producer;

import com.egen.ordermanagement.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Service
@Slf4j
public class ProducerServiceImpl {

    private final KafkaTemplate<String, OrderDto> orderDtokafkaTemplate;

    @Value("${kafka.topic.order.name}")
    private String JSON_TOPIC;

    public ProducerServiceImpl(KafkaTemplate<String, OrderDto> orderDtokafkaTemplate) {
        this.orderDtokafkaTemplate = orderDtokafkaTemplate;
    }

    public void sendOrderData(OrderDto orderDto){
        log.info(String.format("$$$$ => Producing message: %s",orderDto));

        orderDtokafkaTemplate.executeInTransaction(t ->{
            ListenableFuture<SendResult<String,OrderDto>> future =  t .send(JSON_TOPIC,
                    orderDto.getCustomerId() + UUID.randomUUID().toString(),orderDto);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDto>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Unable to produce message [ {} ] due to: {}",orderDto,throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrderDto> stringOrderDtoSendResult) {
                    log.info("Sent Message [ {} ] with offset=[ {} ]",orderDto,
                            stringOrderDtoSendResult.getRecordMetadata().offset());
                }
            });
            return true;
        });
    }
}