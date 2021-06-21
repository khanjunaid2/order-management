package com.egen.controller;

import com.egen.model.Order;
import com.egen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    /**
     * implement the following endpoints
     */

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAllOrders(){
        //TODO
        //return ResponseEntity.ok(Collections.singletonList(new Order("id")));
        return orderService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Order getOrderById(@PathVariable("id") String id){
        //TODO
        return orderService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "interval")
    public List<Order> getAllOrdersWithInInterval(@RequestParam(name = "StartTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime){
        return orderService.findWithInterval(startTime, endTime);
    }

    @RequestMapping(method = RequestMethod.GET, value = "zip/{zip}")
    public List<Order> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        //
        return orderService.findTop10OrderWithHighestDollarInZip(zip);
    }

    public Order placeOrder(@RequestBody Order order, @PathVariable("id") String id){
        return orderService.cancelOrder(order,id);
    }

    public Order cancelOrder(@RequestBody Order order, @PathVariable("id") String id){
        return orderService.cancelOrder(order,id);
    }

    public Order updateOrder(@PathVariable("id") String id){
        return orderService.updateOrder(id);
    }
}
