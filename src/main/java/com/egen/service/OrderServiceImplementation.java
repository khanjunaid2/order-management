package com.egen.service;

import com.egen.exception.ResourceNotFound;
import com.egen.model.Order;
import com.egen.model.OrderEnum;
import com.egen.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrderRepoImplementation orderRepo;



    @Transactional
    public List<Order> findAll() {
        return (List<Order>) orderRepo.findAll();
    }

    @Transactional
    public Optional<Order> findOne(String id) {
        return orderRepo.findById(id);
    }

    @Transactional
    public List<Order> findWithInterval(Timestamp start, Timestamp end) {
        List<Order> res = orderRepo.getAllOrdersWithinInterval(start,end);
        if(res.isEmpty())
        {
            throw new ResourceNotFound("Order not found within" + start + " and " + end);
        }
        return res;
    }

    @Transactional
    public List<Order> findTop10OrderWithHighestDollarInZip(String zip) {
        List<Order> res =  orderRepo.findByZip(zip);
        if(res.isEmpty())
        {
            throw new ResourceNotFound("Order not found with zipcode " + zip);
        }
        res.stream().limit(10).sorted(Comparator.comparing(Order::getSubTotal)).collect(Collectors.toList());
        return res;
    }

    @Transactional
    public Order cancelOrder(Order order, String id) {
        Optional<Order> res = orderRepo.findById(id);
        if(!res.isPresent())
        {
            throw new ResourceNotFound("order not found");
        }
        res.get().setOrderStatus(OrderEnum.CANCELED);
        return (Order) orderRepo.save(res.get());
    }

    @Transactional
    public Order placeOrder(Order order)
    {
        Optional<Order> res = orderRepo.findById(order.getId());
        if(res.isPresent())
        {
            throw new ResourceNotFound("Order already exist");
        }
        return (Order) orderRepo.save(order);
    }

    @Transactional
    public Order updateOrder(Order order) {
        Optional<Order> res = orderRepo.findById(order.getId());
        if(!res.isPresent())
        {
            throw new ResourceNotFound("Order not exist");
        }
        return (Order) orderRepo.save(order);
    }
}
