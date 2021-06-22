package com.egen.controller;

import com.egen.model.Order;
import com.egen.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@Api(value = "/order",description = "Performing operations on Order ")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("order")
    @ApiOperation(value = "Find all orders",notes = "Returns list of all orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("order/{id}")
    @ApiOperation(value = "Find order by id",notes = "Returns order using order id")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id){
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("order/within-interval")
    public ResponseEntity<List<Order>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime){

        return ResponseEntity.ok(orderService.getAllOrdersWithInInterval(startTime,endTime));
    }

    @GetMapping("order/top-10-zipcode/{zip}")
    public ResponseEntity<List<Order>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return ResponseEntity.ok(orderService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping("order")
    @ApiOperation(value = "Create order",notes = "Place new order")
    public ResponseEntity<Order> placeOrder(@RequestBody  Order order){
        Order obj = orderService.placeOrder(order);
        return ResponseEntity.ok(obj);
    }


    @PutMapping("order/cancel/{id}")
    @ApiOperation(value = "Cancel order",notes = "Cancel order by changing the status")
    public ResponseEntity<Order> cancelOrder(@PathVariable("id") long id){
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

    @PutMapping("order/{id}")
    @ApiOperation(value = "Update order",notes = "Update order data")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") long id,  @RequestBody Order order){
        return  ResponseEntity.ok(orderService.updateOrder(order));
    }
}
