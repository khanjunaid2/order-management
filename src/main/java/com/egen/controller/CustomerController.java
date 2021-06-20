package com.egen.controller;

import com.egen.model.Customer;
import com.egen.model.Order;
import com.egen.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("customer")
    public ResponseEntity<List<Customer>> getAllOrders(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getOrderById(@PathVariable("id") long id){
        Customer cust = customerService.getById(id);
        return ResponseEntity.ok(cust);
    }

    @PostMapping("customer")
    public ResponseEntity<Customer> create(@RequestBody Customer cust){
        Customer obj = customerService.create(cust);
        return ResponseEntity.ok(obj);
    }


    @DeleteMapping("customer/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        customerService.delete(id);
        return (ResponseEntity) ResponseEntity.ok();
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") long id,  @RequestBody Customer cust){
        return  ResponseEntity.ok(customerService.update(cust));
    }
}
