//package com.egen.passport.demo.controller.kafka;
//
//import com.egen.passport.demo.dto.OrderDTO;
//import com.egen.passport.demo.service.kafka.producer.ProducerServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//@RequestMapping("/order")
//public class OrderController {
//
//    private final ProducerServiceImpl producerService;
//
//
//    public OrderController(ProducerServiceImpl producerService) {
//        this.producerService = producerService;
//    }
//
//    @PostMapping(value="/publish")
//    public String sendMessageToKafkaTopic(@RequestBody OrderDTO order){
//        log.info("Order received in Order Controller: {}", order);
//        producerService.sendOrderData(order);
//        return "order received";
//    }
//}
