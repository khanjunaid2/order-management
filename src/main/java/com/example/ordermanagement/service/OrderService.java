package com.example.ordermanagement.service;

import com.example.ordermanagement.models.Orders;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    List<Orders> getAllOrders();
    Orders getOrderById(String id);
    List<Orders> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
    List<Orders> top10OrdersWithHighestDollarAmountInZip(String zip);
    Orders placeOrder(Orders orders);
    Orders cancelOrder(String id);
    Orders updateOrder(Orders orders, String id);
}
