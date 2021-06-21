package com.egen.service;

import com.egen.model.Address;
import com.egen.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressServiceImplementation implements AddressService{

    @Autowired
    AddressRepo addressRepo;

    @Transactional
    public Address createAdd(Address address) {
        return addressRepo.create(address);
    }
}
