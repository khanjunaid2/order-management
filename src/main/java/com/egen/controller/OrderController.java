package com.egen.controller;

import com.egen.entity.Order;
import com.egen.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("orderServiceImpl")
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    @ApiOperation(value = "Get al orders", notes = "return a list of all orders in the database")
    public List<Order> getAllOrders() {
        //TODO
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public Order getOrderById(@PathVariable("id") String orderId) {
        //TODO
        return orderService.getOrderById(orderId);
    }

    @GetMapping("{start}/{end}")
    public List<Order> getAllOrdersWithinInterval(@PathVariable("start") Timestamp startTime, @PathVariable("end") Timestamp endTime) {
        //TODO
        return orderService.getAllOrdersWithinInterval(startTime, endTime);
    }

    @GetMapping("getTopTen/{zip}")
    public List<Order> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip) {
        //TODO
        return orderService.top10OrdersWithHighestDollarAmountInZip(zip);
    }

    @PostMapping("")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @PutMapping("cancel/{id}")
    public Order cancelOrder(@PathVariable("id") String orderId) {
        return orderService.cancelOrder(orderId);
    }

    @PutMapping("update/{id}")
    public Order updateOrder(@PathVariable("id") String orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }
}
