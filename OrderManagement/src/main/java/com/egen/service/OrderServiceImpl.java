package com.egen.service;

import com.egen.model.Order;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrder() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return (Order) orderRepository.findAllById(Collections.singleton(id));
    }

    public Optional<List<Order>> getAllOrdersWithinInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
        return null;
//        return orderRepository.findAllOrdersWithinInterval(startTime, endTime);
    }

    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
        return null;
//        return orderRepository.top10OrdersWithHighestDollarAmountInZip(zip);
    }

    public Order placeOrder(Order order) {
        return null;
//        return orderRepository.placeOrder(order);
    }

    public Order cancelOrder(String id) {
        return null;
//        return orderRepository.cancelOrder(id);
    }

    public Order updateOrder(String orderId, Order order) {
        return null;
//        return orderRepository.updateOrder(orderId, order);
    }
}
