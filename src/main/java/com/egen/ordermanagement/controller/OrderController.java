package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import com.egen.ordermanagement.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Api(tags = {"orders"})
@SwaggerDefinition(tags = {@Tag(name = "orders", description = "Order Service Endpoints")})
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ApiOperation(value = "Find all orders",
                  notes = "Returns a list of all available orders")
    public Response<List<OrderDTO>> getAllOrders() {

        return Response.<List<OrderDTO>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrders())
                .build();
    }

    @GetMapping(value = "/pagination", params = {"pageNumber", "pageSize", "sortBy"})
    @ApiOperation(value = "Find all sorted orders with pagination",
                  notes = "Returns a list of all sorted orders with pagination")
    public ResponseEntity<List<OrderDTO>> getAllOrdersWithPaginationAndSorted(@RequestParam(defaultValue = "0") int pageNumber,
                                                       @RequestParam(defaultValue = "5") int pageSize,
                                                       @RequestParam(defaultValue = "creationDate") String sortBy) {

        return new ResponseEntity<>(orderService.getAllOrdersWithPaginationAndSorted(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find an order",
                  notes = "Returns details of requested order")
    public Response<OrderDTO> getOrderById(@PathVariable("id") String orderId) {

        return Response.<OrderDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getOrderById(orderId))
                .build();
    }

    @GetMapping(value = "/interval/{startTime}/{endTime}")
    @ApiOperation(value = "Find all orders with in the given time interval",
                  notes = "Returns a list of orders, that are placed within the given time interval.")
    public Response<List<OrderDTO>> getAllOrdersWithInInterval(@PathVariable("startTime") Timestamp startTime,
                                                                     @PathVariable("endTime") Timestamp endTime) {
        return Response.<List<OrderDTO>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrdersWithInInterval(startTime, endTime))
                .build();
    }

    @GetMapping (value = "/search", params = {"zip"})
    @ApiOperation(value = "Find top 10 orders in a given location",
                  notes = "Returns a list of top 10 orders with the highest total amount in a given location.")
    public Response<List<OrderDTO>> top10OrdersWithHighestDollarAmountInZip(@RequestParam("zip") String zip) {

        return Response.<List<OrderDTO>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.top10OrdersWithHighestDollarAmountInZip(zip))
                .build();

    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "create an order",
                  notes = "Returns created order data.")
    public Response<OrderDTO> placeOrder(@RequestBody OrderDTO order) {

        return Response.<OrderDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.placeOrder(order))
                .build();
    }

    @PostMapping(value = "/cancel/{id}")
    @ApiOperation(value = "cancel an order",
                  notes = "Returns cancelled order data with order status as cancelled.")
    public Response<OrderDTO> cancelOrder(@PathVariable("id") String orderId) {

       return Response.<OrderDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.cancelOrder(orderId))
                .build();
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "update an order",
                  notes = "Returns modified order.")
    public Response<OrderDTO> updateOrder(@RequestBody OrderDTO order) {

        return Response.<OrderDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.updateOrder(order))
                .build();
    }
}
