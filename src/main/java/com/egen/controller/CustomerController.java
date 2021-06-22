package com.egen.controller;

import com.egen.model.Customer;
import com.egen.model.Order;
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
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("customer/{id}")
    @ApiOperation(value = "Find customer by id",notes = "Returns customer using customer id")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id){
        Customer cust = customerService.getById(id);
        return ResponseEntity.ok(cust);
    }

    @PostMapping("customer")
    @ApiOperation(value = "Create customer",notes = "Insert new customer data")
    public ResponseEntity<Customer> create(@RequestBody Customer cust){
        Customer obj = customerService.create(cust);
        return ResponseEntity.ok(obj);
    }


    @DeleteMapping("customer/{id}")
    @ApiOperation(value = "Delete customer",notes = "Remove customer data")
    public ResponseEntity delete(@PathVariable("id") long id){
        customerService.delete(id);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @PutMapping("customer/{id}")
    @ApiOperation(value = "Update customer",notes = "Update customer data")
    public ResponseEntity<Customer> update(@PathVariable("id") long id,  @RequestBody Customer cust){
        return  ResponseEntity.ok(customerService.update(cust));
    }
}
