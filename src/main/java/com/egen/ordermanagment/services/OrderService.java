package com.egen.ordermanagment.services;

import com.egen.ordermanagment.model.Orders;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    Orders findOne(String id);
    List<Orders> findWithinInterval(Timestamp startTime,Timestamp endTime);
    List<Orders> findTop10OrdersWithHighestDollarAmountInZip(String zip);
    Orders placeOrder(Orders order);
    Orders updateOrder(String id, Orders order);
    Orders cancelOrder(String id);
}
