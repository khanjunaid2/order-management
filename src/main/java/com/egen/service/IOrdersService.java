package com.egen.service;

import com.egen.dtos.ordersDTO;
import com.egen.entity.orders;

import java.util.List;

public interface IOrdersService {
    List<orders> getAllOrders();
    orders getOrderById(String id);
    ordersDTO createOrder(ordersDTO ord);
    orders updateOrder(orders ord);
    void delete(String id);



}
