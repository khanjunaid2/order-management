package com.egen.repository;

import com.egen.model.Address;
import com.egen.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, String> {
    Address address= new Address();
    List<Customer>  findAllById(String id);
}
