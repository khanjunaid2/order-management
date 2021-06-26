package com.egen.kafka.service.producer;


import com.egen.model.Order;
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

    private final KafkaTemplate<String, Order> orderKafkaTemplate;

    @Value("${kafka.topic.json-demo.name}")
    private String JSON_TOPIC;

    public ProducerService(KafkaTemplate<String, Order> orderKafkaTemplate) {
        this.orderKafkaTemplate = orderKafkaTemplate;
    }

    /**
     * To produce Order data.
     * @param message
     *
     * */
    public void sendOrderDataMessage(Order message) {
        log.info(String.format("$$$$ => Producing message: %s", message));

        orderKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, Order>> future = t.send(JSON_TOPIC, String.valueOf(message.getId()), message);
            future.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onSuccess(SendResult<String, Order> result) {
                    log.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.info("Unable to produce message=[ {} ] due to : {}", message, ex.getMessage());
                }

            });
            return true;
        });
    }

}
    