package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findByFirstName(String FirstName);
    List<Customer> findAll();
}
