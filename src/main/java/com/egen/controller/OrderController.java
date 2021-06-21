package com.egen.controller;

import com.egen.model.Orders;
import com.egen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    public OrderService orderService;

    //Get all the orders
    @RequestMapping(method = RequestMethod.GET)
    public List<Orders> getAllOrders(){
        List<Orders> orders = orderService.getAllOrders();
        return orders;
    }

    //Get order by ID
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Orders getOrderById(@PathVariable("id") String oid){
        Orders order = orderService.getOrderById(oid);
        return order;
    }

    //GET by Time Interval
    @RequestMapping(method = RequestMethod.GET, value = "interval")
    public List<Orders> getAllOrdersWithInInterval(@RequestBody Timestamp startTime,@RequestBody Timestamp endTime){
        List<Orders> orders = orderService.getAllOrdersWithInInterval(startTime, endTime);
        return orders;
    }

    //TO
    @RequestMapping(method = RequestMethod.GET,value = "topOrders")
    public List<Orders> top10OrdersWithHighestDollarAmountInZip(@RequestBody String zip){
        System.out.println("======================"+zip);
        List<Orders> orders = orderService.top10OrdersWithHighestDollarAmountInZip(zip);
        return orders;
    }

    //POST order
    @RequestMapping(method = RequestMethod.POST)
    public Orders placeOrder(@RequestBody Orders order){
        orderService.placeOrder(order);
        return order;
    }

    //TO
    @RequestMapping(method = RequestMethod.PUT, value = "/cancel")
    public Orders cancelOrder(@RequestBody Orders order){
        orderService.cancelOrder(order);
        return order;
    }

    //UPDATE order
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Orders updateOrder(@PathVariable("id") String oid, @RequestBody Orders order){
        orderService.updateOrder(oid, order);
        return order;
    }


}
