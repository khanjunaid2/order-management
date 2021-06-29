package com.egen.service.kafka;

import com.egen.dto.OrderDTO;
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

    private final KafkaTemplate<String, OrderDTO> orderItemDTOKafkaTemplate;


    @Value("order-management")
    private String JSON_TOPIC;

    public ProducerService(  KafkaTemplate<String, OrderDTO> orderItemDTOKafkaTemplate) {

        this.orderItemDTOKafkaTemplate = orderItemDTOKafkaTemplate;
    }

    public void sendOrderItemData(OrderDTO orderDTO) {
        log.info(String.format("$$$$ => Producing message: %s", orderDTO));
        System.out.println("Inside the Producer Service before sending data to Topic");
        orderItemDTOKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrderDTO>> future = t.send(JSON_TOPIC, orderDTO.getId(), orderDTO);
            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                public void onSuccess(SendResult<String, OrderDTO> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ] to the Topic", orderDTO, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", orderDTO, ex.getMessage());
                }

            });
            return true;
        });
    }

}

