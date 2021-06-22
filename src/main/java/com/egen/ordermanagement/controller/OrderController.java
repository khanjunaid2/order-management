package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.entity.Order;
import com.egen.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Get all orders without Pagination
    @RequestMapping(method= RequestMethod.GET)
    public List<Order> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    //Get all orders with Pagination
    @RequestMapping(method= RequestMethod.GET,value="/all/{pageNo}/{pageSize}")
    public List<Order> getPaginatedCountries(@PathVariable int pageNo,
                                               @PathVariable int pageSize) {

        return orderService.getAllOrders(pageNo, pageSize);
    }

    @RequestMapping(method = RequestMethod.GET, value="/id/{id}")
    public Order getOrderById(@PathVariable("id") String id){
            return orderService.getOrderById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value="/interval/{start}/{end}")
    public List<Order> getAllOrdersWithInInterval(@PathVariable("start") String startTime, @PathVariable("end") String endTime){
        return orderService.getAllOrdersWithinInterval(startTime, endTime);
    }

    @RequestMapping(method = RequestMethod.GET, value="/zip/{zip}")
    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip){
        return orderService.getTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/cancel/{id}")
    public void cancelOrder(@PathVariable("id") String order){
        orderService.delete(order);
    }

    @RequestMapping(method = RequestMethod.PUT, value={"/update/{id}"})
    public Order updateOrder(@PathVariable("id") String orderId,@RequestBody Order order){
        return orderService.update(orderId, order);
    }
}
