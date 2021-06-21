package com.egen.Services;

import com.egen.Model.Order;
import com.egen.Model.OrderStatus;
import com.egen.Repository.OrderRepo;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderServices implements OrderServiceInterface{

    private final OrderRepo orderrepo;

    public OrderServices (OrderRepo orderrepo){
        this.orderrepo = orderrepo;
    }


    @Override
    public List<Order> getAllOrders() {
        return orderrepo.getAllOrders();
    }

    @Override
    public Order getOrderById(String id) {
        return orderrepo.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersWithTimeInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
        return orderrepo.getOrdersWithTimeInterval(startTime, endTime);
    }

    @Override
    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
        return orderrepo.top10OrdersWithHighestDollarAmountInZip(zip);
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public OrderStatus cancelOrder(String id) {
        return null;
    }

    @Override
    public OrderStatus updateOrder(String id, Order order) {
        return null;
    }
}