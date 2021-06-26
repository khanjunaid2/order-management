package com.egen.passport.demo.repository;

import com.egen.passport.demo.entity.Address;
import com.egen.passport.demo.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
