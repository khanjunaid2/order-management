package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends CrudRepository<Address,Long> {

}
