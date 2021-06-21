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

    /**
     * Get All Order using pagination
     * @param page
     * @param size
     * @return
     */
    @GetMapping
    public List<Orders> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return service.findAll(page, size);
    }

    /**
     * Get order by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable("id") String id){
        return service.findOne(id);
    }

    /**
     * Get All Orders Within Date interval
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/interval")
    public List<Orders> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime,
                                                   @RequestParam(name = "endTime") Timestamp endTime){
        return service.findWithinInterval(startTime,endTime);
    }

    /**
     * Get Top 10 orders with highest total amount
     * @param zip
     * @return
     */
    @GetMapping("/zipcode/{zip}")
    public List<Orders> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return service.findTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    /**
     * Create Order
     * @param order
     * @return
     */
    @PostMapping
    public Orders placeOrder(@RequestBody Orders order){
        service.placeOrder(order);
        return order;
    }

    /**
     * Cancel Order. Update OrderStatus as "CANCELLED"
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    public Orders cancelOrder(@PathVariable("id") String id){
        return service.cancelOrder(id);
    }

    /**
     * Update Order
     * @param id
     * @param order
     * @return
     */
    @PutMapping("/update/{id}")
    public Orders updateOrder(@PathVariable("id") String id, @RequestBody Orders order){
        return service.updateOrder(id, order);
    }

}

