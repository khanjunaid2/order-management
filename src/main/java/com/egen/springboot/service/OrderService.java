package com.egen.service;

import com.egen.model.Order;

import java.time.ZonedDateTime;
import java.util.List;

public interface OrderService {
    List<Order> findAllOrders();

    Order findOrderById(String id);

    List<Order> findAllOrdersWithInInterval(ZonedDateTime startTime, ZonedDateTime endTime);

    List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

    Order saveOrder(Order order);

    Order cancelOrder(Order order);

    Order updateOrder(Order order);
}
