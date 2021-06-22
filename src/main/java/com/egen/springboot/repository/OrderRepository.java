package com.egen.repository;

import com.egen.model.Order;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

public interface OrderRepository {
    List<Order> findAllOrders();

    Order findOrderById(String orderId);

    List<Order> findAllOrdersWithInInterval(ZonedDateTime startTime, ZonedDateTime endTime);

    List<Order> OrdersWithHighestDollarAmountInZip(String zip);

    Order saveOrder(Order order);

    Order updateOrder(Order order);

    Order cancelOrder(Order order);
}
