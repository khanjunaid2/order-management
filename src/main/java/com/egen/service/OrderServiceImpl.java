package com.egen.service;

import com.egen.dto.OrderDTO;
import com.egen.enums.OrderStatus;
import com.egen.exception.ResourceNotFoundException;
import com.egen.model.*;
import com.egen.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderServiceImpl() {

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return (List<OrderDTO>) orderRepository.findAll();
    }

    @Override
    public Order getOrderById(String orderId) {
        Optional<Order> existing = orderRepository.findById(orderId);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Order with this" + orderId + "doesn't exist.");
        }
        return existing.get();
    }

    @Override
    public List<Order> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        List<Order> existing =orderRepository.getAllOrdersWithInInterval(startTime,endTime);
        if(existing.isEmpty()){
            throw new ResourceNotFoundException("No orders placed between" +startTime+ "and" +endTime+ ".");
        }
        return existing;
    }

    @Override
    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Order> existing = orderRepository.findByzip(zip);
        if(existing.isEmpty()){
            throw new ResourceNotFoundException("Order with this" + zip + "doesn't exist.");
        }
        existing.stream()
                .limit(10).
                sorted(Comparator.comparingDouble(Order::getTotalAmount)).
                collect(Collectors.toList());
        return existing;
    }

    @Override
    public OrderDTO placeOrder(Order order) {
        Optional<Order> existing = orderRepository.findById(order.getId());
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Order with this" + order.getId() + "doesn't exist.");
        }
        return (OrderDTO) orderRepository.save(order);
    }

    @Override
    public OrderDTO cancelOrder(String orderId) {
        Optional<Order> existing = orderRepository.findById(orderId);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Order with this" + orderId + "doesn't exist.");
        }

        existing.get().setOrderStatus(OrderStatus.CANCEL);
        return (OrderDTO) orderRepository.save(existing.get());
    }

    @Override
    public OrderDTO updateOrder(String id, Order order) {
        Optional<Order> existing = orderRepository.findById(order.getId());
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Order with this" + order.getId() + "doesn't exist.");
        }
        return (OrderDTO) orderRepository.save(order);
    }

}