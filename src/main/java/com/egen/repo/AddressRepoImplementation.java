package com.egen.repo;

import com.egen.model.Address;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AddressRepoImplementation implements AddressRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address create(Address address) {
        entityManager.persist(address);
        return address;
    }
}
