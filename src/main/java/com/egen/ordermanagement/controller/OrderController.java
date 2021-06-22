package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.entity.Order;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import com.egen.ordermanagement.service.OrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //Get all orders without Pagination
    @ApiOperation(value="Find all orders",
    notes="Returns a list of orders")
    @ApiResponses(value={
            @ApiResponse(code = 200,message="OK"),
            @ApiResponse(code = 500,message="Internal Server Error")
    })
    @RequestMapping(method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Order>> getAllOrders()
    {
//        return orderService.getAllOrders();
        return Response.<List<Order>>builder()
                .meta(ResponseMetadata.builder().statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name())
                        .build()).data((orderService.getAllOrders())).build();
    }

    //Get all orders with Pagination
    @ApiOperation(value="Find all orders with Pagination",
            notes="Returns a list of orders regarding to pageSize")
    @ApiResponses(value={
            @ApiResponse(code = 200,message="OK"),
            @ApiResponse(code = 500,message="Internal Server Error")
    })
    @RequestMapping(method= RequestMethod.GET,value="/all/{pageNo}/{pageSize}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getPaginatedCountries(@ApiParam(value="Page Number") @PathVariable int pageNo,
                                             @ApiParam(value="Page Size") @PathVariable int pageSize) {

        return orderService.getAllOrders(pageNo, pageSize);
    }

    //API for getOrderByID
    @RequestMapping(method = RequestMethod.GET, value="/id/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Find order using ID",
            notes="Returns order")
    @ApiResponses(value={
            @ApiResponse(code = 200,message="OK"),
            @ApiResponse(code = 500,message="Internal Server Error")
    })
    public Response<Order> getOrderById(@ApiParam(value="Order ID") @PathVariable("id") String id){
//            return orderService.getOrderById(id);
        return Response.<Order>builder()
                .meta(ResponseMetadata.builder().statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name())
                        .build()).data((orderService.getOrderById(id)))
                .build();
    }


    //API for getAllOrdersWithInInterval
    @RequestMapping(method = RequestMethod.GET, value="/interval/{start}/{end}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Find all orders within interval",
            notes="Returns list of orders within interval")
    @ApiResponses(value={
            @ApiResponse(code = 200,message="OK"),
            @ApiResponse(code = 500,message="Internal Server Error")
    })
    public List<Order> getAllOrdersWithInInterval(
            @ApiParam(value="StartDate") @PathVariable("start") String startTime,
            @ApiParam(value="End Date") @PathVariable("end") String endTime){
        return orderService.getAllOrdersWithinInterval(startTime, endTime);
    }

    //API for top10OrdersWithHighestDollarAmountInZip
    @RequestMapping(method = RequestMethod.GET, value="/zip/{zip}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Find top 10 orders with Highest Dollar Amount In Zip",
            notes="Returns list of orders with Highest Dollar Amount In Zip")
    @ApiResponses(value={
            @ApiResponse(code = 200,message="OK"),
            @ApiResponse(code = 500,message="Internal Server Error")
    })
    public List<Order> top10OrdersWithHighestDollarAmountInZip(
            @ApiParam(value="ZIP") @PathVariable("zip") String zip){
        return orderService.getTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    //API for placeOrder
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    //API for cancelOrder
    @RequestMapping(method = RequestMethod.DELETE, value="/cancel/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void cancelOrder(@PathVariable("id") String order){
        orderService.delete(order);
    }

    //API for updateOrder
    @RequestMapping(method = RequestMethod.PUT, value={"/update/{id}"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Order updateOrder(@PathVariable("id") String orderId,@RequestBody Order order){
        return orderService.update(orderId, order);
    }
}
