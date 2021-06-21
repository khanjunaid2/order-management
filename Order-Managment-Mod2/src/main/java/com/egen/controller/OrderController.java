package com.egen.controller;

import com.egen.model.GroceryOrder;
import com.egen.service.IOrderManagementService;
import com.egen.service.impl.OrderManagementService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@RestController
public class OrderController {
    /**
     * implement the following endpoints
     */
    private IOrderManagementService orderManagementService;

    public OrderController(IOrderManagementService orderManagementService){
        this.orderManagementService = orderManagementService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<GroceryOrder>> getAllOrders(){
        return ResponseEntity.ok(orderManagementService.getAllOrders());
    }

    @GetMapping("order/{id}")
    public ResponseEntity<GroceryOrder> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(orderManagementService.getOrderById(id));
    }

    @GetMapping("ordersInInterval/{startTime}/{endTime}")
    public ResponseEntity<List<GroceryOrder>> getAllOrdersWithInInterval(@PathVariable Timestamp startTime, @PathVariable Timestamp endTime){
        return ResponseEntity.ok(orderManagementService.getAllOrdersWithInInterval(startTime,endTime));
    }

    @GetMapping("topOrdersInZip/{zip}")
    public ResponseEntity<List<GroceryOrder>> top10OrdersWithHighestDollarAmountInZip(@PathVariable String zip){
        return ResponseEntity.ok(orderManagementService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping("/save")
    public ResponseEntity<GroceryOrder> placeOrder(@RequestBody GroceryOrder order){
        return new ResponseEntity<>(orderManagementService.saveOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public ResponseEntity<GroceryOrder> cancelOrder(@RequestBody GroceryOrder order){
        return ResponseEntity.ok(orderManagementService.cancelOrder(order));
    }

    @PutMapping("/update")
    public ResponseEntity<GroceryOrder> updateOrder(@RequestBody GroceryOrder order){
        return ResponseEntity.ok(orderManagementService.updateOrder(order));
    }


}
