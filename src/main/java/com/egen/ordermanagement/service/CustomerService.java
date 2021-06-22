package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.CustomerDto;

public interface CustomerService {
    boolean findCustomer(Long id);
    boolean createCustomer(CustomerDto customerDto);

}
