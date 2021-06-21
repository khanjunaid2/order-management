package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.exceptions.ExistingCustomerException;
import com.egen.ordermanagement.model.Customer;
import com.egen.ordermanagement.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Transactional(readOnly = true)
    public boolean findCustomer(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isPresent())
            return true;
        else
            return false;
    }

    @Transactional
    public Customer createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepo.findByEmail(customerDto.getEmail());
        if(customer.isPresent())
            throw new ExistingCustomerException("The email:"+ customerDto.getEmail()
                    +" is already registered please SignIn");
        else
        return customerRepo.save(new Customer(customerDto.getFirstName(),
                customerDto.getLastName(),customerDto.getEmail()));
    }
}
