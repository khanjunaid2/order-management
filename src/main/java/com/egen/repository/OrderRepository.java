package com.egen.repository;

import com.egen.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {

    List<Order> findAllByDateCreatedBetween(Timestamp startTime, Timestamp endTime);

    List<Order> findTop10ByShippingIdOrderByTotal(String zip);
}
