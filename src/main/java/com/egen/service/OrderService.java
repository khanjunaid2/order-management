package com.egen.service;

import com.egen.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
        List<Order> getAllOrders();

        Order getOrderById(String orderId);

        List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);

        List<Order> top10OrdersWithHighestDollarAmountInZip(String zip);

        Order placeOrder(Order order);

        Order cancelOrder(String orderId);

        Order updateOrder(Order order);



}

