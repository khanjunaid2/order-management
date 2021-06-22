package com.egen.controller;

import com.egen.model.Customer;
import com.egen.model.Order;
import com.egen.response.Response;
import com.egen.response.ResponseMetadata;
import com.egen.response.StatusMessage;
import com.egen.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@Api(value = "/order",description = "Performing operations on Order ")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("order")
    @ApiOperation(value = "Find all orders",notes = "Returns list of all orders")
    public Response<List<Order>> getAllOrders(){
        List<Order> orderList = orderService.getAllOrders();
        return orderList.isEmpty() == Boolean.FALSE ?
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data(orderList)
                        .build()
                :
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data(null)
                        .build();
    }


    @GetMapping(value = "order/pagination", params = {"pageNumber", "pageSize", "sortBy"})
    @ApiOperation(value = "Find all orders",notes = "Returns list of all orders")
    public Response<List<Order>> getAllOrdersByPaginationAndSorting(@RequestParam(name = "pageNumber") int pageNum,
                                                                    @RequestParam(name="pageSize") int pageSize,
                                                                    @RequestParam(name = "sortBy") String sortBy){
        List<Order> orderList = orderService.getAllOrdersByPaginationAndSorting(pageNum,pageSize,sortBy);
        return orderList.isEmpty() == Boolean.FALSE ?
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data(orderList)
                        .build()
                :
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data(null)
                        .build();
    }

    @GetMapping("order/{id}")
    @ApiOperation(value = "Find order by id",notes = "Returns order using order id")
    public Response<Order> getOrderById(@PathVariable("id") long id){
        Order order = orderService.getOrderById(id);
        return Response.<Order>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(order)
                .build();
    }

    @GetMapping("order/within-interval")
    public Response<List<Order>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime){
        List<Order> orderList = orderService.getAllOrdersWithInInterval(startTime,endTime);
        return orderList.isEmpty() == Boolean.FALSE ?
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data(orderList)
                        .build()
                :
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data(null)
                        .build();
    }

    @GetMapping("order/top-10-zipcode/{zip}")
    public Response<List<Order>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        List<Order> orderList =orderService.top10OrdersWithHighestDollarAmountInZip(zip);
        return orderList.isEmpty() == Boolean.FALSE ?
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data(orderList)
                        .build()
                :
                Response.<List<Order>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data(null)
                        .build();
    }

    @PostMapping("order")
    @ApiOperation(value = "Create order",notes = "Place new order")
    public Response<Order> placeOrder(@RequestBody  Order order){
        Order obj = orderService.placeOrder(order);
        return Response.<Order>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(obj)
                .build();
    }


    @PutMapping("order/cancel/{id}")
    @ApiOperation(value = "Cancel order",notes = "Cancel order by changing the status")
    public Response<Order> cancelOrder(@PathVariable("id") long id){
        return  Response.<Order>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.cancelOrder(id))
                .build();
    }

    @PutMapping("order/{id}")
    @ApiOperation(value = "Update order",notes = "Update order data")
    public Response<Order> updateOrder(@PathVariable("id") long id,  @RequestBody Order order){
        return  Response.<Order>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(orderService.updateOrder(order))
                .build();
    }
}
