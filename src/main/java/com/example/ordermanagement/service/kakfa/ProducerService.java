package com.example.ordermanagement.service.kakfa;

import com.example.ordermanagement.DTO.OrdersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, OrdersDto> ordersDtoKafkaTemplate;

    @Value("${kafka.topic.name}")
    private String JSON_TOPIC;


    public ProducerService(KafkaTemplate<String, OrdersDto> ordersDtoKafkaTemplate) {
        this.ordersDtoKafkaTemplate = ordersDtoKafkaTemplate;
    }


    public void sendOrderCreatedMessage(OrdersDto order){
        log.info(String.format("$$$$ => Producing Order message: %s",order));

        ordersDtoKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrdersDto>> future = t.send(JSON_TOPIC, order.getOrderId() ,order);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrdersDto>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Unable to produce message = [ {} ] due to : {}", order, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrdersDto> stringOrdersDtoSendResult) {
                    log.info("Sent Order Created Message = [ {} ] with offset= [ {} ]", order, stringOrdersDtoSendResult.getRecordMetadata().offset());
                }
            });
            return true;
        });
    }
}
