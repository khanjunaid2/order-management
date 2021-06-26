package com.egen.Controller;

import com.egen.DTO.OrderDTO;
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

    private OrderServices orderService;

    public OrderController (OrderServices orderService){
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        //TODO
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "id") String id){
        //TODO
        return ResponseEntity.ok(this.orderService.getOrderById(id));
    }

    @GetMapping(value = "/orderInInterval", params = { "startTime", "endTime" })
    public ResponseEntity<List<OrderDTO>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") ZonedDateTime startTime,
                                                                  @RequestParam(name = "endTime") ZonedDateTime endTime){
        //TODO
        return ResponseEntity.ok(this.orderService.getOrdersWithTimeInterval(startTime, endTime));
    }

    @GetMapping(value = "/top10OrderByZip", params = {"zip"})
    public ResponseEntity<List<OrderDTO>> top10OrdersWithHighestDollarAmountInZip(@RequestParam(name = "zip") String zip){
        //TODO
        return ResponseEntity.ok(this.orderService.top10OrdersWithHighestDollarAmountInZip(zip));
    }

    @PostMapping(value = "/addOrder")
    public void placeOrder(@RequestBody OrderDTO order){
        this.orderService.addOrder(order);
    }

    @PutMapping(value = "/cancelOrder/{id}")
    public ResponseEntity<OrderStatus> cancelOrder(@PathVariable(name ="id") String id){
        return ResponseEntity.ok(this.orderService.cancelOrder(id));
    }

    @PutMapping(value = "/updateOrder/{id}")
    public ResponseEntity<OrderStatus> updateOrder(@PathVariable(name = "id") String id, @RequestBody OrderDTO order){
        return ResponseEntity.ok(this.orderService.updateOrder(id, order));
    }
}

