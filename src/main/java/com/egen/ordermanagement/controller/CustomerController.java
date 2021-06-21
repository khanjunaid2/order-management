package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.model.Customer;
import com.egen.ordermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /**
     * This function is used to create a new user
     * @return - returns HTTP status as created and details of the new customer
     */
    @RequestMapping(method = RequestMethod.POST,value = "/register",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }
}
