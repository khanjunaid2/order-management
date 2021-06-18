package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.model.Customer;

public interface CustomerService {
    boolean findCustomer(Long id);
    Customer createCustomer(CustomerDto customerDto);

}
