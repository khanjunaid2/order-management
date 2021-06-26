package com.egen.ordermanagement.service.kafka.producer;

import com.egen.ordermanagement.dto.OrderDTO;
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
public class ProducerServiceImpl {
    private final KafkaTemplate<String, OrderDTO> orderKafkaTemplate;

    @Value("$kafka.topic.order.name")
    private String ORDER_TOPIC;

    public ProducerServiceImpl(KafkaTemplate<String, OrderDTO> orderKafkaTemplate){
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    public void sendOrderData(OrderDTO orderDTO){
        log.info(String.format("$$$ => Producing Message: ",orderDTO));

        orderKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String,OrderDTO>> future = t.send(ORDER_TOPIC, orderDTO.getCustomerId() + UUID.randomUUID(), orderDTO);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderDTO>>() {
                @Override
                public void onFailure(Throwable e) {
                    log.info("Unable to produce message=[{}] due to {}",orderDTO, e.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrderDTO> result) {
                    log.info("Sent Message=[{}] with offset=[{}]",orderDTO,result.getRecordMetadata().offset());
                }
            });
            return true;
        });
    }

}