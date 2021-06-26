package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import com.egen.ordermanagement.service.CustomerService;
import com.egen.ordermanagement.service.kafka.producer.ProducerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
@Slf4j
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ProducerServiceImpl producerService;
    /**
     * This function is used to create a new user
     * @return - returns HTTP status as created and details of the new customer
     */
    @PostMapping(value="/register",consumes = "application/json",produces = "application/json")
    public Response<Boolean> createCustomer(@RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto) == Boolean.TRUE ?
                Response.<Boolean>builder().meta(ResponseMetadata.builder().statusCode(201)
                        .statusMessage(StatusMessage.CREATED.name())
                        .build()).data(true).build()
                :
                Response.<Boolean>builder().meta(ResponseMetadata.builder().statusCode(409)
                        .statusMessage(StatusMessage.CONFLICT.name())
                        .build()).data(false).build();
    }
}
