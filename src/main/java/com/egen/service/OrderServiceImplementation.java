package com.egen.service;

import com.egen.model.Order;
import com.egen.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrderRepoImplementation orderRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    ItemsRepo itemsRepo;
    @Autowired
    PaymentsRepo paymentsRepo;
    @Autowired
    CustomerRepo customerRepo;


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
        return orderRepo.findWithinInterval(start,end);
    }

    @Transactional
    public List<Order> findTop10OrderWithHighestDollarInZip(String zip) {
        return orderRepo.findTop10OrdersWithHighestDollarAmountInZip(zip);
    }

    @Transactional
    public Order cancelOrder(Order order, String id) {
        return orderRepo.cancelOrder(id, order);
    }

    @Transactional
    public Order updateOrder(String id) {
        return orderRepo.updateOrder(id);
    }
}
