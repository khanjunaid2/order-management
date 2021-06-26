package com.egen.controller;


import com.egen.dtos.ordersDTO;
import com.egen.entity.orders;
import com.egen.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ordersController {

    @Autowired
    private IOrdersService service;

//    @RequestMapping(method= RequestMethod.GET,value="/orders")
//    private List<orders>getAllOrders()
//    {
//        return service.getAllOrders();
//    }
//
//    @RequestMapping(method= RequestMethod.GET,value="/orders/{id}")
//    private orders getOrderById(@PathVariable("id") String id)
//    {
//        return service.getOrderById(id);
//    }
//
//    @RequestMapping(method= RequestMethod.POST,value="/orders")
//    private orders createOrder(@RequestBody orders ord)
//    {
//        return service.createOrder(ord);
//    }
//    @RequestMapping(method= RequestMethod.PUT,value="/orders")
//    private orders updateOrder(@RequestBody orders ord)
//    {
//        return service.updateOrder(ord);
//    }
//    @RequestMapping(method= RequestMethod.DELETE,value="/orders/{id}")
//    private void delete(@PathVariable("id") String id)
//    {
//        service.delete(id);
//    }

    @GetMapping(value="/orders")
    private List<orders> getAllOrders()
    {
        return service.getAllOrders();
    }

    @GetMapping(value="/orders/{id}")
    private orders getOrderById(@PathVariable("id") String id)
    {
        return service.getOrderById(id);
    }

    @PostMapping(value="/orders")
    private ordersDTO createOrder(@RequestBody ordersDTO ord)
    {
        return service.createOrder(ord);
    }
    @PutMapping(value="/orders")
    private orders updateOrder(@RequestBody orders ord)
    {
        return service.updateOrder(ord);
    }
    @DeleteMapping(value="/orders/{id}")
    private void delete(@PathVariable("id") String id)
    {
        service.delete(id);
    }


}
