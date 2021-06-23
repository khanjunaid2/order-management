package com.egen.passport.demo.service;

import com.egen.passport.demo.dto.OrderDTO;
import com.egen.passport.demo.entity.CustomerOrder;

import java.util.List;

public interface OrderService {
    Boolean createOrder(OrderDTO orderDto);
    CustomerOrder getOrderById(Long id);
    public Boolean createOrders(List<OrderDTO> orderDto);
    public List<CustomerOrder> getAllOrders();
    public List<CustomerOrder> getAllOrders(int from , int to);
    public List<CustomerOrder> getAllOrdersSortBy(int from , int to, String sortBy);
}
