package com.egen.ordermanagment.controller;

import com.egen.ordermanagment.model.Orders;
import com.egen.ordermanagment.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {
    /**
     * implement the following endpoints
     */
    @Autowired
    private OrderService service;

    @GetMapping
    public List<Orders> getAllOrders(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable("id") String id){
        return service.findOne(id);
    }

    @GetMapping("/interval")
    public List<Orders> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime,
                                                   @RequestParam(name = "endTime") Timestamp endTime){
        return service.findWithinInterval(startTime,endTime);
    }

    @GetMapping("/zipcode/{zip}")
    public List<Orders> getAllOrdersWithInInterval(@PathVariable("zip") String zip){
        return service.findTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    @PostMapping
    public Orders placeOrder(@RequestBody Orders order){
        service.placeOrder(order);
        return order;
    }

    @PutMapping("/cancel/{id}")
    public Orders cancelOrder(@PathVariable("id") String id){
        return service.cancelOrder(id);
    }

    @PutMapping("/update/{id}")
    public Orders updateOrder(@PathVariable("id") String id, @RequestBody Orders order){
        return service.updateOrder(id, order);
    }

}

