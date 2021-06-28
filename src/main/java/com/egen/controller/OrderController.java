package com.egen.controller;

import com.egen.dto.OrderDto;
import com.egen.response.Response;
import com.egen.response.ResponseMetadata;
import com.egen.response.StatusMessage;
import com.egen.service.OrderService;
import com.egen.service.kafka.OrderProducerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderProducerServiceImpl orderProducerService;

    public OrderController(OrderService orderService, OrderProducerServiceImpl orderProducerService) {
        this.orderService = orderService;
        this.orderProducerService = orderProducerService;
    }

    @ApiOperation(value = "Gets Orders", nickname = "getOrders", notes = "Get all the orders")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping
    public Response<List<OrderDto>> getAllOrders() {
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrders())
                .build();
    }

    /**
     *
     * Gets new order
     * Passes to orderProducerService
     *
     */
    @ApiOperation(value = "Create Order ", nickname = "createOrders", notes = "Creates new order")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @PostMapping
    public Response<String> placeOrder(@RequestBody OrderDto orderDTO) {
        return orderProducerService.sendOrder(orderDTO) ? Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Order got processed in Kafka")
                .build()
                :Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(400)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order failed to load")
                        .build();
    }

    @ApiOperation(value = "Order pages", nickname = "getOrdersPages", notes = "Gets the Orders in pages")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping(value = "/page/{pageNumber}")
    public Response<List<OrderDto>> getAllOrdersPagination(@PathVariable("pageNumber") int pagenumber) {
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getOrderPage(pagenumber))
                .build();
    }

    @ApiOperation(value = "Get order by ID", nickname = "getOrderById", notes = "Get the orders by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping(value = "{id}")
    public Response<OrderDto> getOrderById(@PathVariable("id") String id) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getOrderById(id)))
                .build();
    }

    @ApiOperation(value = "Orders within interval", nickname = "getOrdersInTime", notes = "Gets the orders created within dates")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping(value = "interval")
    public Response<List<OrderDto>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime) {
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.getAllOrdersWithInInterval(startTime, endTime))
                .build();
    }

    @ApiOperation(value = "Gets top orders by zip", nickname = "getTopOrdersByZip", notes = "Gets the orders by zip which have highest amount")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @GetMapping(value = "zipcode/{zip}")
    public Response<List<OrderDto>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip) {
        return Response.<List<OrderDto>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.top10OrdersWithHighestDollarAmountInZip(zip))
                .build();
    }


    @ApiOperation(value = "Cancel order", nickname = "cancelOrder", notes = "Cancels the Placed order")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @PutMapping(value = "/cancel/{id}")
    public Response<OrderDto> cancelOrder(@PathVariable("id") String id) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.cancelOrder(id))
                .build();
    }

    @ApiOperation(value = "Updates the order", nickname = "updateOrders", notes = "update the order by new values")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval")})
    @PutMapping(value = "{id}")
    public Response<OrderDto> updateOrder(@PathVariable("id") String oid, @RequestBody OrderDto orderDTO) {
        return Response.<OrderDto>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.updateOrder(oid, orderDTO))
                .build();
    }
}
