package com.egen.ordermanagement.service;

import com.egen.ordermanagement.model.GroceryOrder;
import com.egen.ordermanagement.model.OrderStatus;
import com.egen.ordermanagement.repository.OrderManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class OrderManagementServiceImpl implements OrderManagementService{
    @Autowired
    private OrderManagementRepository repository;
    @Override
    public GroceryOrder saveOrder(GroceryOrder order) {
        return repository.save(order);
    }

    @Override
    public GroceryOrder getOrderById(String id) {
       return repository.findById(id).get();
    }

    @Override
    public GroceryOrder getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        return null;
    }

    @Override
    public GroceryOrder top10OrdersWithHighestDollarAmountInZip(String zip) {
       return null;
    }

    @Override
    public void cancelOrder(GroceryOrder order) {
        GroceryOrder orderToBeCancelled = order;
        orderToBeCancelled.setOrderStatus(OrderStatus.CANCEL);
        repository.save(orderToBeCancelled);
    }

    @Override
    public GroceryOrder updateOrder(GroceryOrder order) {
        return repository.save(order);
    }
    @Override
    public GroceryOrder createRandomOrders(int num) {
        return null;
    }

    @Override
    public List<GroceryOrder> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public GroceryOrder updateOrderById(String id) {
//        GroceryOrder order = repository.findById(id).get();
         return null;
    }
}
