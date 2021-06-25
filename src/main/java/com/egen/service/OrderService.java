package com.egen.service;

import com.egen.model.Order;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();
    Optional<Order> findOne(String id);
    List<Order> findWithInterval(Timestamp start, Timestamp end);
    List<Order> findTop10OrderWithHighestDollarInZip(String zip);
    Order cancelOrder(Order order, String id);
    Order placeOrder(Order order);
    Order updateOrder(Order order);
}
