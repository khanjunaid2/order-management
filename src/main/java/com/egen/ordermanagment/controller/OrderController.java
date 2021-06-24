package com.egen.ordermanagment.controller;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.model.Orders;
import com.egen.ordermanagment.response.Response;
import com.egen.ordermanagment.response.ResponseMetadata;
import com.egen.ordermanagment.response.StatusMessage;
import com.egen.ordermanagment.services.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
@Api(value = "orders management endpoints")
public class OrderController {
    /**
     * implement the following endpoints
     */
    @Autowired
    private OrderService service;

    @GetMapping
    public List<Orders> getAllOrders(){
        return service.getAllOrders();
    }

    /**
     * Get all orders using pagination and sortBy created date
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @GetMapping("/paging")
    @ApiOperation(value = "Fetch All orders",
            notes = "Returns a list of all the orders in the database using pagination and sorting")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No orders found"),
            @ApiResponse(code = 500, message = "Error while retrieving all orders") })
    public Response<List<OrdersDTO>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "orderCreated") String sortBy) {
//        return new ResponseEntity<>(service.findAll(page, size, sortBy), HttpStatus.OK);
        return Response.<List<OrdersDTO>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.findAllPaginationSorting(page, size, sortBy))
                .build();
    }

    /**
     * Get order by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Fetch orders by Id",
            notes = "Returns a single order or throws Exception")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No orders found"),
            @ApiResponse(code = 500, message = "Error while retrieving order with provided Id") })
    public Response<OrdersDTO> getOrderById(
            @ApiParam(value = "Order Id", required = true) @PathVariable("id") String id){
//        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
        return Response.<OrdersDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.findOne(id))
                .build();
    }

    /**
     * Get All Orders Within Date interval
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/interval")
    @ApiOperation(value = "Fetch orders within given interval of start time and end time",
            notes = "Returns orders matching filters or throws Exception")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No orders found"),
            @ApiResponse(code = 500, message = "Error while retrieving orders") })
    public Response<List<OrdersDTO>> getAllOrdersWithInInterval(
            @ApiParam(value = "Order created date from", required = true) @RequestParam(name = "startTime") Timestamp startTime,
            @ApiParam(value = "order created date to", required = true) @RequestParam(name = "endTime") Timestamp endTime){
//        return new ResponseEntity<>(service.findWithinInterval(startTime,endTime), HttpStatus.OK);
        return Response.<List<OrdersDTO>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.findWithinInterval(startTime,endTime))
                .build();
    }

    /**
     * Get Top 10 orders with highest total amount
     * @param zip
     * @return
     */
    @GetMapping("/zipcode/{zip}")
    @ApiOperation(value = "Fetch top 10 orders with highest order amount sorted by zipcode",
            notes = "Returns top 10 orders matching filters or throws Exception")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No orders found"),
            @ApiResponse(code = 500, message = "Error while retrieving orders") })
    public Response<List<OrdersDTO>> top10OrdersWithHighestDollarAmountInZip(
            @ApiParam(value = "zipcode of customer who created order", required = true) @PathVariable("zip") String zip){
//        return new ResponseEntity<>(service.findTop10OrdersWithHighestDollarAmountInZip(zip), HttpStatus.OK);
        return Response.<List<OrdersDTO>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.findTop10OrdersWithHighestDollarAmountInZip(zip))
                .build();
    }

    /**
     * Create Order
     * @param order
     * @return
     */
    @PostMapping
    @ApiOperation(value = "",
            notes = "Creates Order using raw json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "created"),
            @ApiResponse(code = 500, message = "Error while retrieving orders") })
    public Response<OrdersDTO> placeOrder(@RequestBody OrdersDTO order) {
//        return new ResponseEntity<>(service.placeOrder(order), HttpStatus.CREATED);
        return Response.<OrdersDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.placeOrder(order))
                .build();
    }

    /**
     * Cancel Order. Update OrderStatus as "CANCELLED"
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation(value = "",
            notes = "Updates Order status to Cancel using Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "No order found"),
            @ApiResponse(code = 500, message = "Error while retrieving order") })
    public Response<OrdersDTO> cancelOrder(@PathVariable("id") String id){
        //        return new ResponseEntity<>(service.cancelOrder(id), HttpStatus.OK);
        return Response.<OrdersDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.cancelOrder(id))
                .build();
    }

    /**
     * Update Order
     * @param id
     * @param order
     * @return
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value = "",
            notes = "Updates Order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order updated"),
            @ApiResponse(code = 500, message = "Error while updating order") })
    public Response<OrdersDTO> updateOrder(@PathVariable("id") String id, @RequestBody OrdersDTO order){
//        return new ResponseEntity<>(service.updateOrder(id, order), HttpStatus.OK);
        return Response.<OrdersDTO>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(service.updateOrder(id, order))
                .build();
    }


}

