package com.egen.service.kafka;

import com.egen.dto.OrderItemDTO;
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
//    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, OrderItemDTO> orderItemDTOKafkaTemplate;

//    @Value("${kafka.topic.json-demo.name}")
    @Value("${spring.kafka.topic.ordermanagment.name}")
    private String JSON_TOPIC;

    public ProducerService(  KafkaTemplate<String, OrderItemDTO> orderItemDTOKafkaTemplate) {

        this.orderItemDTOKafkaTemplate = orderItemDTOKafkaTemplate;
    }

    public void sendOrderItemData(OrderItemDTO orderItemDTO) {
        log.info(String.format("$$$$ => Producing message: %s", orderItemDTO));
        System.out.println("Inside the Producer Service before sending data to Topic");
        orderItemDTOKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrderItemDTO>> future = t.send(JSON_TOPIC, orderItemDTO.getOrderId(), orderItemDTO);
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, OrderItemDTO> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ] to the Topic", orderItemDTO, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", orderItemDTO, ex.getMessage());
                }

            });
            return true;
        });
    }

}
