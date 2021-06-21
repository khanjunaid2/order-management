package com.egen.ordermanagment.services;

import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.OrderStatus;
import com.egen.ordermanagment.model.Orders;
import com.egen.ordermanagment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Orders> findAll() {
        return (List<Orders>) orderRepository.findAll();
    }

    @Override
    public Orders findOne(String id) {
        Optional<Orders> existing = orderRepository.findById(id);
        if(!existing.isPresent()){
            throw new OrderServiceException("Order with id"+ id + "not found");
        }
        return existing.get();
    }

    @Override
    public List<Orders> findWithinInterval(Timestamp startTime, Timestamp endTime) {
        List<Orders> orders = orderRepository.findAllByOrderCreatedBetween(startTime,endTime);
        if(orders.isEmpty()){
            throw new OrderServiceException("Order between this interval not found");
        }
        return orders;
    }

    @Override
    public List<Orders> findTop10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Orders> ordersWithMaxAmount = orderRepository.findTop10OrdersWithHighestDollarAmountInZip(zip);
        if(ordersWithMaxAmount.isEmpty()){
            throw new OrderServiceException("Orders with zip "+ zip +" not found");
        }
        return ordersWithMaxAmount;
    }

    @Override
    public Orders placeOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public Orders updateOrder(String id, Orders order) {
        Optional<Orders> existing = orderRepository.findById(id);
        if(!existing.isPresent()){
            // ResourceNotFoundException
            throw new OrderServiceException("Order with id"+ id + "not found to update");
        }
        return orderRepository.save(order);
    }

    @Override
    public Orders cancelOrder(String id) {
        Optional<Orders> existing = orderRepository.findById(id);
        if(!existing.isPresent()){
            // ResourceNotFoundException
            throw new OrderServiceException("Order with id"+ id + "not found to update");
        }
        existing.get().setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(existing.get());
    }
}

