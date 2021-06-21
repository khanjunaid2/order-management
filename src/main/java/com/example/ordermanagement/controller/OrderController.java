package com.example.ordermanagement.controller;

import com.example.ordermanagement.models.Orders;
import com.example.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping(value = "/paging", params = {"pageNo", "pageSize", "sortBy"})
    public ResponseEntity<List<Orders>> getAllOrdersByPagingAndSortedByTotalAmount(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy){
        return ResponseEntity.ok(orderService.getAllOrdersByPagingAndSorting(pageNo, pageSize, sortBy));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("id") String id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping(value = "/intervals/{startTime}/{endTime}")
    public ResponseEntity<List<Orders>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime){
        return  ResponseEntity.ok(orderService.getAllOrdersWithInInterval(startTime, endTime));
    }

    @GetMapping(value = "topOrdersInZip/{zip}")
    public ResponseEntity<List<Orders>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return ResponseEntity.ok(orderService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping(value = "/placeorder")
    public ResponseEntity<Orders> placeOrder(@RequestBody Orders orders){
        return ResponseEntity.ok(orderService.placeOrder(orders));
    }

    @PutMapping(value = "/cancelorder/{id}")
    public ResponseEntity<Orders> cancelOrder(@PathVariable("id") String id){
        return ResponseEntity.ok(orderService.cancelOrder(id));
    }

    @PutMapping(value = "/updateorder/{id}")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders, @PathVariable("id") String id){
        return ResponseEntity.ok(orderService.updateOrder(orders, id));
    }
}
