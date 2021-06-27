package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.model.GroceryOrder;
import com.egen.ordermanagement.repository.OrderManagementRepository;
import com.egen.ordermanagement.service.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.util.List;

@RestController
public class OrderManagementController {
    @Autowired
    private OrderManagementService service;

    @GetMapping("orders")
    public List<GroceryOrder> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("order/{id}")
    public ResponseEntity<GroceryOrder> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PutMapping("order/{id}")
    public ResponseEntity<GroceryOrder> updateOrderById(@PathVariable String id){
        return ResponseEntity.ok(service.updateOrderById(id));
    }

    @GetMapping("ordersInInterval/{startTime}/{endTime}")
    public ResponseEntity<List<GroceryOrder>> getAllOrdersWithInInterval(@PathVariable Timestamp startTime, @PathVariable Timestamp endTime){
        return null;
    }

    @GetMapping("topOrdersInZip/{zip}")
    public ResponseEntity<List<GroceryOrder>> top10OrdersWithHighestDollarAmountInZip(@PathVariable String zip){
        return null;
    }

    @PostMapping("/save")
    public ResponseEntity<GroceryOrder> placeOrder(@RequestBody GroceryOrder order){
        return new ResponseEntity<>(service.saveOrder(order), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public void cancelOrder(@RequestBody GroceryOrder order){
        service.cancelOrder(order);
    }

    @PutMapping("/update")
    public ResponseEntity<GroceryOrder> updateOrder(@RequestBody GroceryOrder order){
        return ResponseEntity.ok(service.updateOrder(order));
    }
}

