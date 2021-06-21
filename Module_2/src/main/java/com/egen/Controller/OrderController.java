package com.egen.Controller;

import com.egen.Model.Order;
import com.egen.Model.OrderStatus;
import com.egen.Services.OrderServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
public class OrderController {
    /**
     * implement the following endpoints
     */

    private OrderServices orderservice;

    public OrderController (OrderServices orderservice){
        this.orderservice = orderservice;
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getAllOrders(){
        //TODO
        return ResponseEntity.ok(this.orderservice.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "id") String id){
        //TODO
        return ResponseEntity.ok(this.orderservice.getOrderById(id));
    }

    @GetMapping(value = "/orderInInterval", params = { "startTime", "endTime" })
    public ResponseEntity<List<Order>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") ZonedDateTime startTime,
                                                                  @RequestParam(name = "endTime") ZonedDateTime endTime){
        //TODO
        return ResponseEntity.ok(this.orderservice.getOrdersWithTimeInterval(startTime, endTime));
    }

    @GetMapping(value = "/top10OrderByZip", params = {"zip"})
    public ResponseEntity<List<Order>> top10OrdersWithHighestDollarAmountInZip(@RequestParam(name = "zip") String zip){
        //TODO
        return ResponseEntity.ok(this.orderservice.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping(value = "/addOrder")
    public void placeOrder(@RequestBody Order order){
        this.orderservice.addOrder(order);
    }

    @PutMapping(value = "/cancelOrder/{id}")
    public ResponseEntity<OrderStatus> cancelOrder(@PathVariable(name ="id") String id){
        return ResponseEntity.ok(this.orderservice.cancelOrder(id));
    }

    @PutMapping(value = "/updateOrder/{id}")
    public ResponseEntity<OrderStatus> updateOrder(@PathVariable(name = "id") String id, @RequestBody Order order){
        return ResponseEntity.ok(this.orderservice.updateOrder(id, order));
    }
}

