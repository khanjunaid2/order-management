package com.egen.controller;

import com.egen.dto.CustomerDTO;
import com.egen.model.Customer;
import com.egen.model.Order;
import com.egen.response.Response;
import com.egen.response.ResponseMetadata;
import com.egen.response.StatusMessage;
import com.egen.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/customer",description = "Performing operations on Customer ")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("customer")
    @ApiOperation(value = "Find all customer",notes = "Returns list of all customer")
    public Response<List<Customer>> getAllCustomers(){

        List<Customer> customerList= customerService.findAll();

        return customerList.isEmpty() == Boolean.FALSE ?
                Response.<List<Customer>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.SUCCESS.name()).build())
                        .data(customerList)
                        .build()
                :
                Response.<List<Customer>>builder()
                        .meta(ResponseMetadata.builder()
                                .statusCode(200)
                                .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                        .data(null)
                        .build();
    }


    @GetMapping("customer/{id}")
    @ApiOperation(value = "Find customer by id",notes = "Returns customer using customer id")
    public Response<Customer> getCustomerById(@PathVariable("id") long id){
        Customer cust = customerService.getById(id);
        return Response.<Customer>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(cust)
                .build();
    }

    @PostMapping("customer")
    @ApiOperation(value = "Create customer",notes = "Insert new customer data")
    public Response<Customer> create(@RequestBody Customer cust){
        Customer obj = customerService.create(cust);
        return  Response.<Customer>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(obj)
                .build();
    }


    @DeleteMapping("customer/{id}")
    @ApiOperation(value = "Delete customer",notes = "Remove customer data")
    public Response<String> delete(@PathVariable("id") long id){
        customerService.delete(id);
        return Response.<String>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.UNKNOWN_INTERNAL_ERROR.name()).build())
                .data("Customer deleted")
                .build();
    }

    @PutMapping("customer/{id}")
    @ApiOperation(value = "Update customer",notes = "Update customer data")
    public Response<Customer> update(@PathVariable("id") long id,  @RequestBody Customer cust){
        return   Response.<Customer>builder()
                .meta(ResponseMetadata.builder()
                        .statusCode(200)
                        .statusMessage(StatusMessage.SUCCESS.name()).build())
                .data(customerService.update(cust))
                .build();
    }
}
