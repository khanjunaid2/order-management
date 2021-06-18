package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.OrderDto;
import com.egen.ordermanagement.model.Orders;

import java.sql.Timestamp;
import java.util.List;

public interface OrdersService {
    List<Orders> findAll();
    Orders findOne(Long id);
    List<Orders> findWithinInterval(Timestamp startTime, Timestamp endTime);
    List<Orders> findTop10OrdersWithHighestDollarAmountInZip(String zip);
    Orders createOrder(OrderDto orderDto);
    Orders cancelOrder(Long id);
    Orders updateOrder(OrderDto orderDto,Long id);
}
