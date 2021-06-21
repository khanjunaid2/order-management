package com.egen.repo;

import com.egen.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepo {

    List<Order> findall();
    Order findOne(String id);
    List<Order> findWithinInterval(Timestamp start, Timestamp end);
    List<Order> findTop10OrdersWithHighestDollarAmountInZip(String zip);
    Order creteOrder(Order order);
    Order cancelOrder(String id, Order order);
    Order updateOrder(String id);
}
