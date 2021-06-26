package com.egen.ordermanagement.controller;


import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import com.egen.ordermanagement.service.CustomerService;
import com.egen.ordermanagement.service.ItemService;
import com.egen.ordermanagement.service.OrdersService;
import com.egen.ordermanagement.service.kafka.producer.ProducerServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Api(description = "Order related endpoints")
@Slf4j
public class OrdersController {

    @Autowired
    CustomerService customerService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    ItemService itemService;

    @Autowired
    ProducerServiceImpl producerService;

    /**
     * Creates a new order
     * @param orderDto
     * @return
     */
    @PostMapping(value = "/publish/order")
    @ApiOperation(value = "Creates a new order",
            notes = "Refer the Order DTO class to know the Json format")
    @ApiResponses(value={
            @ApiResponse(code=201,message = "CREATED"),
            @ApiResponse(code=500,message = "INTERNAL SERVER ERROR"),
            @ApiResponse(code=200,message = "OK")
    })
    public String publishOrder(@RequestBody OrderDto orderDto){
        log.info("Order Received in Order Controller:{}",orderDto);
         producerService.sendOrderData(orderDto);
         return "Order Received";
    }
    /**
     * Fetches all the orders present in the table
     * @return list of orders
     */
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Fetches all the orders",
                    notes = "Returns all the orders")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=500,message = "Interval Server Error"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<List<Orders>> getAllOrders(){
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder().statusCode(302)
                        .statusMessage(StatusMessage.FOUND.name())
                        .build()).data((ordersService.findAll()))
                .build();
    }

    /**
     * Fetches all the orders and limits the page size
     * @param pageNo - set the page number -default 0
     * @param pageSize - set the page size -default -15
     * @return
     */
    @GetMapping(produces = "application/json",value = "/pagelimit")
    @ApiOperation(value = "Fetches all the orders by limiting 15 orders per page by default",
            notes = "Returns 15 orders in one page and can be changed to other values as well")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=500,message = "Interval Server Error"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<List<Orders>> getAllOrdersByPageLimit(@RequestParam(defaultValue = "0")Integer pageNo,
                                                          @RequestParam(defaultValue = "15")Integer pageSize){
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder().statusCode(302)
                        .statusMessage(StatusMessage.FOUND.name())
                        .build()).data((ordersService.findAllByPageLimit(pageNo,pageSize)))
                .build();
    }

    /**
     * Fetches order details of the given id
     * @param id accepts Order id as input
     * @return Order object
     */
    @GetMapping(produces = "application/json",value = "/{id}")
    @ApiOperation(value = "Fetches the order by the given Id",
            notes = "Returns details of a signle order")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=404,message = "Not Found"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<Orders> getOrderById(@ApiParam(value = "Order id of the order",required = true)
                                             @PathVariable("id")Long id){
        return Response.<Orders>builder()
                .meta(ResponseMetadata.builder().statusCode(302)
                .statusMessage(StatusMessage.FOUND.name()).build()).data((ordersService.findOne(id))).build();
    }

    /**
     * Fetches all the records of orders places between the given interval
     * @param startTime - Time of the order placed
     * @param endTime - Time of the order placed
     * @return List of orders
     */
    @GetMapping(value = "/interval",produces = "application/json")
    @ApiOperation(value = "Fetches the order within the given interval of time",
            notes = "Specify the start and end time in Timestamp format")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=404,message = "Not Found"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<List<Orders>> getAllOrdersWithInInterval(@RequestParam(name = "startTime") Timestamp startTime,
                                                   @RequestParam(name = "endTime") Timestamp endTime){
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder().statusCode(302)
                        .statusMessage(StatusMessage.FOUND.name())
                        .build()).data((ordersService.findWithinInterval(startTime,endTime)))
                        .build();
    }

    /**
     * Fetches the top 10 highest amount in the given area
     * @param zip - Zipcode of the address
     * @return List of orders
     */
    @GetMapping(value = "zipcode/{zip}",produces = "application/json")
    @ApiOperation(value = "Fetches list of orders having the according to highest billing amount in a particular area",
            notes = "Provide the zipcode to fetch the details")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=404,message = "Not Found"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<List<Orders>> top10OrdersWithHighestDollarAmountInZip(@PathVariable("zip") String zip){
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder().statusCode(302)
                        .statusMessage(StatusMessage.FOUND.name())
                        .build()).data((ordersService.findAllByShippingAddressZipcodeAndSubTotal(zip)))
                .build();
    }

    /**
     * Creates a new order
     * @param orderDto
     * @return
     */
    @PostMapping(value = "/placeorder",consumes = "application/json",produces = "application/json")
    @ApiOperation(value = "Creates a new order",
            notes = "Refer the Order DTO class to know the Json format")
    @ApiResponses(value={
            @ApiResponse(code=201,message = "CREATED"),
            @ApiResponse(code=500,message = "INTERNAL SERVER ERROR"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<String> placeOrder(@RequestBody OrderDto orderDto){
        return ordersService.createOrder(orderDto) == Boolean.TRUE ? Response.<String>builder()
                .meta(ResponseMetadata.builder().statusCode(201)
                        .statusMessage(StatusMessage.CREATED.name())
                        .build())
                        .data("Order placed successfully")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder().statusCode(500)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name())
                                .build())
                        .data("Failed to place order")
                        .build();
    }

    /**
     * This method will cancel the selected order
     * @param id - Order id
     * @return
     */
    @PutMapping(value = "/cancel/{id}",produces = "application/json")
    @ApiOperation(value = "Cancels the order",
            notes = "Changes the status to cancelled")
    @ApiResponses(value={
            @ApiResponse(code=500,message = "INTERNAL SERVER ERROR"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<Orders>  cancelOrder(@PathVariable("id")Long id){
        return Response.<Orders>builder()
                .meta(ResponseMetadata.builder().statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name())
                        .build())
                .data((ordersService.cancelOrder(id)))
                .build();
    }

    /**
     * Updates the selected order by changing the values desired
     * Currently this method is used to change the order status to delivered
     * @param id - order id
     * @return
     */
    @PutMapping(value ="/update-order/{id}",produces = "application/json",consumes = "application/json")
    @ApiOperation(value = "Changes the status of the order based on user input",
            notes = "Changes the status to desired value")
    @ApiResponses(value={
            @ApiResponse(code=500,message = "INTERNAL SERVER ERROR"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<Orders> updateOrder(@RequestBody Orders orderStatus,@PathVariable("id")Long id){
        return Response.<Orders>builder()
                .meta(ResponseMetadata.builder().statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name())
                        .build())
                .data((ordersService.updateOrder(orderStatus,id)))
                .build();
    }

    /**
     * Sorts the order by the desired field
     * @param pageNo - set the page number -default 0
     * @param pageSize - set the page size -default -15
     * @param sortBy - set the sort by field - default - itemQuantity
     * @return
     */
    @GetMapping(produces = "application/json",value = "/sortby")
    @ApiOperation(value = "Sorts the value according to the value selected by the user",
            notes = "Provide the value which you want to sort")
    @ApiResponses(value={
            @ApiResponse(code=500,message = "INTERNAL SERVER ERROR"),
            @ApiResponse(code=200,message = "OK")
    })
    public Response<List<Orders>> getSortedValues(@RequestParam(defaultValue = "0")Integer pageNo,
                                                          @RequestParam(defaultValue = "15")Integer pageSize,
                                                  @RequestParam(defaultValue = "itemQuantity")String sortBy){
        return Response.<List<Orders>>builder()
                .meta(ResponseMetadata.builder().statusCode(302)
                        .statusMessage(StatusMessage.FOUND.name())
                        .build()).data((ordersService.sortByValues(pageNo,pageSize,sortBy)))
                .build();
    }

}
