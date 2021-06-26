package org.example.controller;

import org.example.entity.CustomerOrder;
import org.example.response.Response;
import org.example.response.ResponseMetadata;
import org.example.response.StatusMessage;
import org.example.service.OrderService;
import org.example.service.kafka.producer.ProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService service;
    private ProducerServiceImpl producerService;

    @Autowired
    public OrderController(OrderService service, ProducerServiceImpl producerService){
        this.service = service;
        this.producerService = producerService;
    }

    @GetMapping
    public Response<List<CustomerOrder>> getAllOrders(){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getAllOrders())
                .build();
    }

    @GetMapping(value = "/{id}")
    public Response<CustomerOrder> getOrderById(@PathVariable("id") String id){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getOrderById(id))
                .build();
    }

    @GetMapping(value = "/ordersInInterval/{startTime}/{endTime}")
    public Response<List<CustomerOrder>> getAllOrdersWithInInterval(@PathVariable("startTime") Timestamp startTime, @PathVariable("endTime") Timestamp endTime){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getAllOrdersWithInInterval(startTime, endTime))
                .build();
    }

    @GetMapping(value = "/topOrdersInZip/{zip}")
    public Response<List<CustomerOrder>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getTop10OrdersWithHighestDollarAmountInZip(zip))
                .build();
    }

    @PostMapping(value = "/createOrder")
    public Response<CustomerOrder> placeOrder(@RequestBody CustomerOrder order){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.placeOrder(order))
                .build();
    }

    @PostMapping(value = "/publishOrder")
    public String publishOrder(@RequestBody CustomerOrder order){
        producerService.sendOrder(order);
        return "Order Received";
    }

    @PostMapping(value = "/cancelOrder/{id}")
    public Response<CustomerOrder> cancelOrder(@PathVariable("id") String id){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.cancelOrder(id))
                .build();
    }

    @PutMapping(value = "/update")
    public Response<CustomerOrder> updateOrder(@RequestBody CustomerOrder order){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.updateOrder(order))
                .build();
    }

    @GetMapping(value = "/pagination", params = {"pageNumber", "pageSize", "sortBy"})
    public Response<List<CustomerOrder>> getAllOrdersWithPaginationAndSorted(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "orderCreated") String sortBy){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getAllOrdersWithPaginationAndSorted(pageNumber, pageSize, sortBy))
                .build();
    }
}
