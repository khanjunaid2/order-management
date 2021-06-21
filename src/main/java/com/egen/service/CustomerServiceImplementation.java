package com.egen.service;

import com.egen.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImplementation implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Transactional
    public boolean findCust(String id) {
        return customerRepo.findCust(id);
    }
}
