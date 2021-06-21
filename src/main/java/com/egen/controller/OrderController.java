package com.egen.controller;

import com.egen.model.OrderItem;
import com.egen.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.time.ZonedDateTime;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * implement the following endpoints
     */

    @GetMapping("orders")
    public ResponseEntity<List<OrderItem>> getAllOrders(){
        //TODO
        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Optional<OrderItem>> getOrderById(@PathVariable String id){
        //TODO
        return   ResponseEntity.ok(service.getOrderById(id));
    }

    @GetMapping("/ordersinterval/{startTime}/{endTime}")
    public ResponseEntity<List<OrderItem>> getAllOrdersWithInInterval(@PathVariable Timestamp startTime, @PathVariable Timestamp endTime){
        //TODO
        return ResponseEntity.ok(service.getAllOrdersWithInInterval(startTime,endTime));
    }

    @GetMapping("/toporders/{zip}")
    public ResponseEntity<List<OrderItem>> top10OrdersWithHighestDollarAmountInZip(@PathVariable String zip){
        //TODO
        return ResponseEntity.ok(service.top10OrdersWithHighestDollarAmountInZip(zip));
    }
    @PostMapping("/save")
    public ResponseEntity<OrderItem> placeOrder(@RequestBody OrderItem orderItem){
        return ResponseEntity.ok(service.placeOrder(orderItem));
    }

    @PutMapping("/cancel")
    public ResponseEntity<OrderItem> cancelOrder(OrderItem order){
        return ResponseEntity.ok(service.cancelOrder(order));
    }

    @PutMapping("/update")
    public ResponseEntity<OrderItem> updateOrder(OrderItem order){
        return ResponseEntity.ok(service.updateOrder(order));
    }
    @PostMapping("/random/{num}")
    public ResponseEntity<String> createRandomOrders(@PathVariable int num){
        return new ResponseEntity<>(service.createRandomOrders(num), HttpStatus.CREATED);
    }
}
