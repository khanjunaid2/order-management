package com.egen.controller;

import com.egen.model.Order;
import com.egen.service.OrderService;
import com.egen.service.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    
    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orderList = orderService.getAllOrder();
        return ResponseEntity.ok(orderList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") String id){
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/order/{startTime}/{endTime}")
    public ResponseEntity<Optional<List<Order>>> getAllOrdersWithInInterval(@PathVariable("startTime") ZonedDateTime startTime, @PathVariable("endTime") ZonedDateTime endTime){
        Optional<List<Order>> orderList = orderService.getAllOrdersWithinInterval(startTime, endTime);
        return ResponseEntity.ok(orderList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTop10OrdersInZip/{zip}")
    public ResponseEntity<List<Order>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
    	 List<Order> orderList = orderService.top10OrdersWithHighestDollarAmountInZip(zip);
         return ResponseEntity.ok(orderList);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/placeOrder")
    public ResponseEntity<Order> placeOrder(Order order){
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(placedOrder);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/cancelOrder")
    public ResponseEntity<Order> cancelOrder(Order order){
        Order cancelledOrder = orderService.cancelOrder(order.getOrderId());
    	return ResponseEntity.ok(cancelledOrder);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateOrder")
    public ResponseEntity<Order> updateOrder(Order order){
        Order updatedOrder = orderService.updateOrder(order.getOrderId(), order);
        return ResponseEntity.ok(updatedOrder);
    }
}
