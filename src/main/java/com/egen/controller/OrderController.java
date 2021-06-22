package com.egen.controller;

import com.egen.model.Order;
import com.egen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("order")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") String orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping("interval/{startTime}/{endTime}")
    public ResponseEntity<List<Order>> getAllOrdersWithInInterval(@PathVariable Timestamp startTime, @PathVariable Timestamp endTime){
        return ResponseEntity.ok(orderService.getAllOrdersWithInInterval(startTime,endTime));
    }

    @GetMapping("orderwithzip/{zipcode}")
    public ResponseEntity<List<Order>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zipcode") String zip){
        return ResponseEntity.ok(orderService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order){
        return new ResponseEntity(orderService.placeOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable("id") String orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

}