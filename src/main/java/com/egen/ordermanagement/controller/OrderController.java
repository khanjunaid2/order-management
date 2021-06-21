package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {

        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping(value = "/pagination", params = {"pageNumber", "pageSize", "sortBy"})
    public ResponseEntity<List<OrderDTO>> getAllOrdersWithPaginationAndSorted(@RequestParam(defaultValue = "0") int pageNumber,
                                                       @RequestParam(defaultValue = "5") int pageSize,
                                                       @RequestParam(defaultValue = "creationDate") String sortBy) {

        return new ResponseEntity<>(orderService.getAllOrdersWithPaginationAndSorted(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") String orderId) {

        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping(value = "/interval/{startTime}/{endTime}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersWithInInterval(@PathVariable("startTime") Timestamp startTime,
                                                                     @PathVariable("endTime") Timestamp endTime) {

        return new ResponseEntity<>(orderService.getAllOrdersWithInInterval(startTime, endTime), HttpStatus.OK);
    }

    @GetMapping (value = "/search", params = {"zip"})
    public ResponseEntity<List<OrderDTO>> top10OrdersWithHighestDollarAmountInZip(@RequestParam("zip") String zip) {

        return new ResponseEntity<>(orderService.top10OrdersWithHighestDollarAmountInZip(zip), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO order) {
        return new ResponseEntity<>(orderService.placeOrder(order), HttpStatus.CREATED);
    }

    @PostMapping(value = "/cancel/{id}")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable("id") String orderId) {
        return new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO order) {
        return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
    }
}
