package com.egen.controller;

import com.egen.entity.Order;
import com.egen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("order")
public class OrderController {
    /**
     * implement the following endpoints
     */

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<Order> getAllOrders(){
        //TODO
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable("id") String orderId){
        //TODO
        return orderService.getOrderById(orderId);
    }

    @GetMapping("{start}/{end}")
    public List<Order> getAllOrdersWithinInterval(@PathVariable("start") Timestamp startTime, @PathVariable("end") Timestamp endTime){
        //TODO
        return orderService.getAllOrdersWithinInterval(startTime, endTime);
    }

    @GetMapping("getTopTen/{zip}")
    public List<Order> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        //TODO
        return orderService.top10OrdersWithHighestDollarAmountInZip(zip);
    }

    @PostMapping("")
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    @PutMapping("cancel/{id}")
    public Order cancelOrder(@PathVariable("id") String orderId){
        return orderService.cancelOrder(orderId);
    }

    @PutMapping("update/{id}")
    public Order updateOrder(@PathVariable("id") String orderId, @RequestBody Order order){
        return orderService.updateOrder(orderId, order);
    }
}
