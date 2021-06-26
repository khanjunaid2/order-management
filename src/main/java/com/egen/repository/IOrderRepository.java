package com.egen.repository;

import com.egen.entity.orders;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepository extends CrudRepository<orders,String> {
}
