package com.egen.springboot.controller;

import com.egen.model.Order;
import com.egen.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class OrderController {
    /**
     * implement the following endpoints
     */
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @GetMapping("ordersInInterval/{startTime}/{endTime}")
    public ResponseEntity<List<Order>> getAllOrdersWithInInterval(@PathVariable ZonedDateTime startTime, @PathVariable ZonedDateTime endTime) {
        return ResponseEntity.ok(orderService.findAllOrdersWithInInterval(startTime, endTime));
    }

    @GetMapping("topOrdersInZip/{zip}")
    public ResponseEntity<List<Order>> top10OrdersWithHighestDollarAmountInZip(@PathVariable String zip) {
        return ResponseEntity.ok(orderService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping("/save")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {

        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public ResponseEntity<Order> cancelOrder(@RequestBody Order order) {

        return ResponseEntity.ok(orderService.cancelOrder(order));
    }

    @PutMapping("/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {

        return ResponseEntity.ok(orderService.updateOrder(order));
    }

}
