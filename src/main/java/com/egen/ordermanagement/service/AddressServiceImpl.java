package com.egen.ordermanagement.service;

import com.egen.ordermanagement.exceptions.AddressServiceException;
import com.egen.ordermanagement.exceptions.OrderServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepo addressRepo;

    @Transactional
    public Address createAddress(Address address) {
        return Optional.ofNullable(addressRepo.save(address))
                .orElseThrow(() -> new AddressServiceException("Unable to add the address"));
    }
}
