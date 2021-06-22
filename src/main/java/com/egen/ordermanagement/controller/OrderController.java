package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.model.Order;
import com.egen.ordermanagement.repository.OrderRepository;
import com.egen.ordermanagement.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping(method = RequestMethod.GET, value = "api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    public OrderController(){}

    @GetMapping("/all")
    private ResponseEntity<List<Order>> getAllOrders(){
        //log.info("***************** "+(orderService.getAllOrders()));
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    private ResponseEntity<Order> getOrderById(@PathVariable long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDTO orderDto){
        Order order = new Order();
        BeanUtils.copyProperties(order, orderDto);
        return new ResponseEntity(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }


    @GetMapping(value = "/interval/{startTime}/{endTime}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersWithInInterval(@PathVariable("startTime") Timestamp startTime,
                                                                     @PathVariable("endTime") Timestamp endTime) {

        Order order = new Order();
        return new ResponseEntity<>(orderService.getAllOrdersWithInInterval(startTime, endTime), HttpStatus.OK);
    }

}
