package com.egen.controller;

import com.egen.dto.OrderItemDTO;
import com.egen.model.OrderItem;
import com.egen.response.Response;
import com.egen.response.ResponseMetadata;

import com.egen.response.StatusMessage;
import com.egen.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.time.ZonedDateTime;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    private OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }

    /**
     * implement the following endpoints
     */

    @GetMapping("orders")
    public Response<List<OrderItem>> getAllOrders(){
        //TODO
//        return ResponseEntity.ok(service.getAllOrders());

        return Response.<List<OrderItem>>builder().meta(ResponseMetadata.builder()
                                                        .statusCode(200)
                                                        .statusMessage(StatusMessage.SUCCESS.name())
                                                        .build())
                                .data((service.getAllOrders())).build();

    }
    @GetMapping("ordersByPage/{page}/{size}")
    public Response<List<OrderItem>> ordersByPage(@PathVariable Integer page,@PathVariable Integer size){
        //TODO
//        return ResponseEntity.ok(service.getAllOrders());

        return Response.<List<OrderItem>>builder().meta(ResponseMetadata.builder()
                .statusCode(200)
                .statusMessage(StatusMessage.SUCCESS.name())
                .build())
                .data((service.pageFindAll(page,size))).build();

    }
    @GetMapping("/order/{id}")
    public Response<Optional<OrderItem>> getOrderById(@PathVariable String id){
        //TODO
//        return   ResponseEntity.ok(service.getOrderById(id));

        return Response.<Optional<OrderItem>>builder().meta(ResponseMetadata.builder()
                .statusCode(200)
                .statusMessage(StatusMessage.SUCCESS.name())
                .build())
                .data((service.getOrderById(id))).build();
    }

    @GetMapping("/ordersinterval/{startTime}/{endTime}")
    public Response<List<OrderItem>> getAllOrdersWithInInterval(@PathVariable Timestamp startTime, @PathVariable Timestamp endTime){
        //TODO
//        return ResponseEntity.ok(service.getAllOrdersWithInInterval(startTime,endTime));
        return Response.<List<OrderItem>>builder().meta(ResponseMetadata.builder()
                .statusCode(200)
                .statusMessage(StatusMessage.SUCCESS.name())
                .build())
                .data((service.getAllOrdersWithInInterval(startTime,endTime))).build();
    }


    @GetMapping("/toporders/{zip}")
    public Response<List<OrderItem>> top10OrdersWithHighestDollarAmountInZip(@PathVariable String zip){
        //TODO
//        return ResponseEntity.ok(service.top10OrdersWithHighestDollarAmountInZip(zip));
        return Response.<List<OrderItem>>builder().meta(ResponseMetadata.builder()
                .statusCode(200)
                .statusMessage(StatusMessage.SUCCESS.name())
                .build())
                .data((service.top10OrdersWithHighestDollarAmountInZip(zip))).build();

    }
    @PostMapping("/save")
    public Response<OrderItemDTO> placeOrder(@RequestBody OrderItemDTO orderItemDTO){
//        return ResponseEntity.ok(service.placeOrder(orderItemDTO));
        return Response.<OrderItemDTO>builder().meta(ResponseMetadata.builder()
                .statusCode(200)
                .statusMessage(StatusMessage.SUCCESS.name())
                .build())
                .data((service.placeOrder(orderItemDTO))).build();
    }

//    @PutMapping("/cancel")
//    public ResponseEntity<OrderItem> cancelOrder(OrderItem order){
//        return ResponseEntity.ok(service.cancelOrder(order));
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<OrderItem> updateOrder(OrderItem order){
//        return ResponseEntity.ok(service.updateOrder(order));
//    }
    @PostMapping("/random/{num}")
    public ResponseEntity<String> createRandomOrders(@PathVariable int num){
        return new ResponseEntity<>(service.createRandomOrders(num), HttpStatus.CREATED);
    }
}
