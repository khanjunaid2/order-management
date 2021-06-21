package com.egen.service;

import com.egen.entity.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(String orderId);

    List<Order> getAllOrdersWithinInterval(Timestamp startTime, Timestamp endTime);

    List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

    Order placeOrder(Order order);

    Order cancelOrder(String id);

    Order updateOrder(String orderId, Order order);

}
