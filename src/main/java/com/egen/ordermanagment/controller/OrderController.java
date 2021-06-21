package com.egen.ordermanagment.controller;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.model.Orders;
import com.egen.ordermanagment.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Get all orders using pagination and sortBy created date
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "orderCreated") String sortBy) {
        return new ResponseEntity<>(service.findAll(page, size, sortBy), HttpStatus.OK);
    }

    /**
     * Get order by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable("id") String id){
        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
    }

    /**
     * Get All Orders Within Date interval
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/interval")
    public ResponseEntity<List<OrdersDTO>> getAllOrdersWithInInterval(
            @RequestParam(name = "startTime") Timestamp startTime,
            @RequestParam(name = "endTime") Timestamp endTime){
        return new ResponseEntity<>(service.findWithinInterval(startTime,endTime), HttpStatus.OK);
    }

    /**
     * Get Top 10 orders with highest total amount
     * @param zip
     * @return
     */
    @GetMapping("/zipcode/{zip}")
    public ResponseEntity<List<OrdersDTO>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return new ResponseEntity<>(service.findTop10OrdersWithHighestDollarAmountInZip(zip), HttpStatus.OK);
    }

    /**
     * Create Order
     * @param order
     * @return
     */
    @PostMapping
    public ResponseEntity<OrdersDTO> placeOrder(@RequestBody OrdersDTO order) {
        return new ResponseEntity<>(service.placeOrder(order), HttpStatus.CREATED);
    }

    /**
     * Cancel Order. Update OrderStatus as "CANCELLED"
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    public ResponseEntity<OrdersDTO> cancelOrder(@PathVariable("id") String id){
        return new ResponseEntity<>(service.cancelOrder(id), HttpStatus.OK);
    }

    /**
     * Update Order
     * @param id
     * @param order
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<OrdersDTO> updateOrder(@PathVariable("id") String id, @RequestBody OrdersDTO order){
        return new ResponseEntity<>(service.updateOrder(id, order), HttpStatus.OK);
    }

}

