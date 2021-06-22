package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.model.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Boolean createOrder(OrderDTO orderDto);
    Order getOrderById(Long id);
    public Boolean createOrders(List<OrderDTO> orderDto);
    public List<Order> getAllOrders();
    List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime);
}
