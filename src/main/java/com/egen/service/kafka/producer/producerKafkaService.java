package com.egen.service.kafka.producer;


import com.egen.dtos.ordersDTO;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class producerKafkaService {

    private final KafkaTemplate<String, ordersDTO> orderDTOKafkaTemplate;
    @Value("${spring.kafka.topic.Module1-2.name}")
    private String JSON_TOPIC;

    public producerKafkaService(  KafkaTemplate<String, ordersDTO> orderDTOKafkaTemplate) {

        this.orderDTOKafkaTemplate = orderDTOKafkaTemplate;
    }

    public void sendOrderItemData(ordersDTO orderDTO) {
        log.info(String.format("$$$$ => Producing message: %s", orderDTO));
        System.out.println("Inside the Producer Service before sending data to Topic");
        orderDTOKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, ordersDTO>> future = t.send(JSON_TOPIC, orderDTO.getId(), orderDTO);
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, ordersDTO> result) {
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
