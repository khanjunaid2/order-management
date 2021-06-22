package com.egen.service;

import com.egen.model.Order;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
        return orders;
    }

    @Override
    public Order findOrderById(String id) {
        Order orders = orderRepository.findOrderById(id);
        return orders;
    }

    @Override
    public List<Order> findAllOrdersWithInInterval(ZonedDateTime startTime, ZonedDateTime endTime) {
        return orderRepository.findAllOrdersWithInInterval(startTime, endTime);
    }

    @Override
    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Order> orders = orderRepository.findAllOrders();
        List<Order> ordersInZip = new LinkedList<>();
        for (Order order : orders) {
            if (order.getShippingAddress().getZip().equalsIgnoreCase(zip)) ordersInZip.add(order);
        }
        Collections.sort(ordersInZip, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Double.compare(o2.getTotalAmount(), o1.getTotalAmount());
            }
        });
        List<Order> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            result.add(ordersInZip.get(i));
        }
        return result;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.saveOrder(order);
    }

    @Override
    public Order cancelOrder(Order orderId) {
        return orderRepository.cancelOrder(orderId);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.updateOrder(order);
    }

}
