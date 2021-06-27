package org.example.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Finds All the Customer Orders",
            notes = "Returns a List of Customer Orders available in Database",
            response = CustomerOrder.class)
    public Response<List<CustomerOrder>> getAllOrders(){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getAllOrders())
                .build();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Finds a particular Customer Order depending on the Id provided",
            notes = "Returns Customer Order",
            response = CustomerOrder.class)
    public Response<CustomerOrder> getOrderById(@PathVariable("id") String id){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getOrderById(id))
                .build();
    }

    @GetMapping(value = "/ordersInInterval/{startTime}/{endTime}")
    @ApiOperation(value = "Finds All the Customer Orders within the given Time Interval",
            notes = "Returns a List of Customer Orders available in Database within the given Time Interval",
            response = CustomerOrder.class)
    public Response<List<CustomerOrder>> getAllOrdersWithInInterval(@PathVariable("startTime") Timestamp startTime, @PathVariable("endTime") Timestamp endTime){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getAllOrdersWithInInterval(startTime, endTime))
                .build();
    }

    @GetMapping(value = "/topOrdersInZip/{zip}")
    @ApiOperation(value = "Finds Top 10 latest Customer Orders at a particular Zipcode",
            notes = "Returns a List of Top 10 Customer Orders available in Database at a particular Zipcode",
            response = CustomerOrder.class)
    public Response<List<CustomerOrder>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getTop10OrdersWithHighestDollarAmountInZip(zip))
                .build();
    }

    @PostMapping(value = "/createOrder")
    @ApiOperation(value = "Creates a Customer Order",
            notes = "Returns the created Customer Order",
            response = CustomerOrder.class)
    public Response<CustomerOrder> placeOrder(@RequestBody CustomerOrder order){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.placeOrder(order))
                .build();
    }

    @PostMapping(value = "/publishOrder")
    @ApiOperation(value = "Creates a Customer Order by sending it first to Kafka and then saving it in Database",
            response = CustomerOrder.class)
    public String publishOrder(@RequestBody CustomerOrder order){
        producerService.sendOrder(order);
        return "Order Received";
    }

    @PostMapping(value = "/cancelOrder/{id}")
    @ApiOperation(value = "Cancels a Customer Order",
            notes = "Returns the cancelled Customer Order",
            response = CustomerOrder.class)
    public Response<CustomerOrder> cancelOrder(@PathVariable("id") String id){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.cancelOrder(id))
                .build();
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "Updates a Customer Order",
            notes = "Returns the Updated Customer Order",
            response = CustomerOrder.class)
    public Response<CustomerOrder> updateOrder(@RequestBody CustomerOrder order){
        return Response.<CustomerOrder>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.updateOrder(order))
                .build();
    }

    @GetMapping(value = "/pagination", params = {"pageNumber", "pageSize", "sortBy"})
    @ApiOperation(value = "Finds all sorted Customer Order with Pagination",
            notes = "Returns a List of all sorted Customer Orders with Pagination",
            response = CustomerOrder.class)
    public Response<List<CustomerOrder>> getAllOrdersWithPaginationAndSorted(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "orderCreated") String sortBy){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.getAllOrdersWithPaginationAndSorted(pageNumber, pageSize, sortBy))
                .build();
    }
}
