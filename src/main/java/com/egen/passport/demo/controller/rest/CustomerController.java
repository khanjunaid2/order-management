package com.egen.passport.demo.controller.rest;


import com.egen.passport.demo.dto.CustomerDTO;
import com.egen.passport.demo.dto.ItemDTO;
import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.*;
import com.egen.passport.demo.repository.CustomerRepository;
import com.egen.passport.demo.repository.OrderRepository;
import com.egen.passport.demo.response.Response;
import com.egen.passport.demo.response.ResponseMetadata;
import com.egen.passport.demo.response.StatusMessage;
import com.egen.passport.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {

    OrderService orderService;

    @Autowired
    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping( value = "/create" , consumes = "application/json", produces = "application/json")
    public Response<String> createOrder(@RequestBody OrderDTO order){
        return orderService.createOrder(order) == Boolean.TRUE ?
         Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data("Order Created")
                .build()
                :
                Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Order Not Created")
                .build();
    }

    @PostMapping( value = "/createOrders" , consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create Bulk Orders", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<String> createOrders(@RequestBody List<OrderDTO> order){
        return orderService.createOrders(order) == Boolean.TRUE ?
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data("Order Created")
                        .build()
                :
                Response.<String>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data("Order Not Created")
                        .build();
    }

    @GetMapping( value = "/getOrderById/{id}" , produces = "application/json")
    public Response<CustomerOrder> getOrderById(@PathVariable String id){
        return Response.<CustomerOrder>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data((orderService.getOrderById(Long.parseLong(id))))
                        .build();
    }

    @GetMapping( value = "/getAllOrders" , produces = "application/json")
    public Response<List<CustomerOrder>> getAllOrders(){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getAllOrders()))
                .build();
    }

    @GetMapping( value = "/getOrdersInRange/{from}/{to}" , produces = "application/json")
    public Response<List<CustomerOrder>> getAllOrders(@PathVariable int from, @PathVariable int to){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getAllOrders(from , to)))
                .build();
    }

    @GetMapping( value = "/getOrdersInRangeAndSort/{from}/{to}/{sortBy}" , produces = "application/json")
    @Operation(summary = "Get all orders with pagination and sorting  ", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    public Response<List<CustomerOrder>> getAllOrdersSortBy(
            @Parameter(name =  "Page Number",
                    example = "1,2,3,4...",
                    required = true)
            @PathVariable int from,
            @Parameter(name =  "Total Records in Each Page",
                    example = "5",
                    required = true)
            @PathVariable int to ,
            @Parameter(name =  "Sort By Functionality",
                    example = "customerId",
                    required = true)
            @PathVariable String sortBy){
        return Response.<List<CustomerOrder>>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data((orderService.getAllOrdersSortBy(from , to , sortBy)))
                .build();
    }


   /* @RequestMapping("/search/{id}")
    public CustomerDTO search(@PathVariable long id){
        Optional<Customer> customer = repository.findById(id);
        repository.saveAndFlush(new Customer());

        CustomerDTO dto = new CustomerDTO();
        if(customer.isPresent()){
            BeanUtils.copyProperties(customer.get() , dto);
            return dto;
        }
       return null;
    }*/

}
