package com.egen.service;

import com.egen.entity.Order;
import com.egen.entity.OrderItem;
import com.egen.enums.OrderStatus;
import com.egen.exception.OrderNotFoundException;
import com.egen.repository.OrderItemRepository;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new OrderNotFoundException(" Order with id: " + orderId + " NOT FOUND");
        }
        return order.get();
    }

    @Transactional
    public List<Order> getAllOrdersWithinInterval(Timestamp startTime, Timestamp endTime) {
        List<Order> orderList = orderRepository.findAllByDateCreatedBetween(startTime, endTime);
        if (orderList == null || orderList.size() == 0) {
            throw new OrderNotFoundException(" Order within interval: " + startTime + " - " + endTime + "NOT FOUND");
        }
        return orderList;
    }

    @Transactional
    public List<Order> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Order> orderList = orderRepository.findTop10ByShippingIdOrderByTotal(zip);
        if (orderList == null || orderList.size() == 0) {
            throw new OrderNotFoundException(" Order in zip: " + zip + "NOT FOUND");
        }
        return orderList;
    }

    @Transactional
    public Order placeOrder(Order order) {
        Order placed = orderRepository.save(order);
        return placed;
    }

    @Transactional
    public Order cancelOrder(String orderId) {
        Optional<Order> existing = orderRepository.findById(orderId);
        if (!existing.isPresent()) {
            throw new OrderNotFoundException(" Order with id: " + orderId + "NOT FOUND");
        }
        Order order = existing.get();
        order.setStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(String orderId, Order order) {
        Optional<Order> existing = orderRepository.findById(orderId);
        if (!existing.isPresent()) {
            throw new OrderNotFoundException(" Order with id: " + orderId + "NOT FOUND");
        }
        return orderRepository.save(order);
    }
}
