package com.egen.service;

import com.egen.exception.BadRequestException;
import com.egen.exception.ResourceNotFoundException;
import com.egen.model.Address;
import com.egen.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {


    @Autowired
    AddressRepository repo;
    public List<Address> findAll() {
        List<Address> list = (List<Address>) repo.findAll();
        if (list.size() == 0) {
            throw new ResourceNotFoundException("Address table is empty");
        }
        return list;
    }

    public Address getById(long id) {
        Optional<Address> existing = repo.findById(id);
        if(!existing.isPresent()) {
            throw new BadRequestException("Address with id " + id + " does not exists");
        }
        Address val=existing.get();
        return val;
    }

    public Address create(Address add) {
        Optional<Address> existing = repo.findById(add.getId());
        if(!existing.isPresent()) {
            throw new BadRequestException("Address with id " + add.getId() + "already exists");
        }
        return repo.save(add);
    }


    public Address update(Address add) {
        Optional<Address> existing = repo.findById(add.getId());
        if(!existing.isPresent()) {
            throw new BadRequestException("Address with id " + add.getId() + "already updated");
        }
        return repo.save(add);
    }

    public void delete(long id){
            Optional<Address> existing = repo.findById(id);
            if(!existing.isPresent()) {
                throw new BadRequestException("Address with id " + id+ "does not exists");
            }
            repo.deleteById(id);
    }
}
