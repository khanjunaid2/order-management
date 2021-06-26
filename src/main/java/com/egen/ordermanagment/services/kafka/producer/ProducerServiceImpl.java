package com.egen.ordermanagment.services.kafka.producer;

import com.egen.ordermanagment.dto.OrdersDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

@Slf4j
@Service
public class ProducerServiceImpl {

    private final KafkaTemplate<String, OrdersDTO> ordersDTOKafkaTemplate;

    @Value("${kafka.topic.order.name}")
    private String ORDER_TOPIC;

    public ProducerServiceImpl(KafkaTemplate<String, OrdersDTO> ordersDTOKafkaTemplate){
        this.ordersDTOKafkaTemplate = ordersDTOKafkaTemplate;
    }

    /**
     * To produce orders data.
     * @param ordersDTO
     *
     * */
    public boolean sendOrdersData(OrdersDTO ordersDTO) {
        log.info(String.format("$$$$ => Producing message: %s", ordersDTO));

        ordersDTOKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrdersDTO>> future = t.send(ORDER_TOPIC, ordersDTO.getCustomerId() + UUID.randomUUID(),  ordersDTO);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrdersDTO>>() {
                @Override
                public void onSuccess(SendResult<String, OrdersDTO> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ]", ordersDTO, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", ordersDTO, ex.getMessage());
                }

            });
            return true;
        });
        return true;
    }
}
