package org.example.controller;

import org.example.entity.CustomerOrder;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService service;

    @Autowired
    public OrderController(OrderService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CustomerOrder>> getAllOrders(){
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerOrder> getOrderById(@PathVariable("id") String id){
        return new ResponseEntity<>(service.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/ordersInInterval/{startTime}/{endTime}")
    public ResponseEntity<List<CustomerOrder>> getAllOrdersWithInInterval(@PathVariable("startTime") Timestamp startTime, @PathVariable("endTime") Timestamp endTime){
        return new ResponseEntity<>(service.getAllOrdersWithInInterval(startTime, endTime), HttpStatus.OK);
    }

    @GetMapping(value = "/topOrdersInZip/{zip}")
    public ResponseEntity<List<CustomerOrder>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return new ResponseEntity<>(service.getTop10OrdersWithHighestDollarAmountInZip(zip), HttpStatus.OK);
    }

    @PostMapping(value = "/createOrder")
    public ResponseEntity<CustomerOrder> placeOrder(@RequestBody CustomerOrder order){
        return new ResponseEntity<>(service.placeOrder(order), HttpStatus.CREATED);
    }

    @PostMapping(value = "/cancelOrder/{id}")
    public ResponseEntity<CustomerOrder> cancelOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(service.cancelOrder(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<CustomerOrder> updateOrder(@RequestBody CustomerOrder order){
        return new ResponseEntity<>(service.updateOrder(order), HttpStatus.OK);
    }

    @GetMapping(value = "/pagination", params = {"pageNumber", "pageSize", "sortBy"})
    public ResponseEntity<List<CustomerOrder>> getAllOrdersWithPaginationAndSorted(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "orderCreated") String sortBy){
        return new ResponseEntity<>(service.getAllOrdersWithPaginationAndSorted(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }
}
