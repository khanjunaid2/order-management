package com.egen.passport.demo.service;

import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.CustomerOrder;

import java.util.List;

public interface OrderService {
    Boolean createOrder(OrderDTO orderDto);
    CustomerOrder getOrderById(Long id);
    public Boolean createOrders(List<OrderDTO> orderDto);
    public List<CustomerOrder> getAllOrders();
}
