package com.egen.ordermanagement.service;

import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepo addressRepo;

    public Address createAddress(Address address) {
        System.out.println("updating address");
        return addressRepo.save(address);
    }
}
