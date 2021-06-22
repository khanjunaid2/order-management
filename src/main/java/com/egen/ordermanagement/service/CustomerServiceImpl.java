package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.CustomerDto;
import com.egen.ordermanagement.exceptions.CustomerServiceException;
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

        try {
            Optional<Customer> customer = customerRepo.findById(id);
         return  customer.isPresent() ? true : false;
        }catch (Exception ex){
            throw new CustomerServiceException("Customer Id: "+id+" not found",ex);
        }
    }

    @Transactional
    public boolean createCustomer(CustomerDto customerDto) {
        try {
            Optional<Customer> customer = customerRepo.findByEmail(customerDto.getEmail());
            if(customer.isPresent())
                throw new Exception();

             customerRepo.save(new Customer(customerDto.getFirstName(),
                    customerDto.getLastName(),customerDto.getEmail()));
             return true;
        }catch (Exception ex){
            throw new CustomerServiceException("The email:"+ customerDto.getEmail()
                    +" is already registered please SignIn",ex);
        }
    }
}
