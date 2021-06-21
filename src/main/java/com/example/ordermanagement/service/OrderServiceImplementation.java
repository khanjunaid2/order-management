package com.example.ordermanagement.service;

import com.example.ordermanagement.enums.OrderStatus;
import com.example.ordermanagement.exceptions.OrderNotFoundException;
import com.example.ordermanagement.models.Orders;
import com.example.ordermanagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    @Transactional
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = (List<Orders>) orderRepository.findAll();
        return ordersList;
    }

    @Override
    public List<Orders> getAllOrdersByPagingAndSorting(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("totalAmount").ascending());
        Page<Orders> pagedResult = orderRepository.findAll(paging);
        if(!pagedResult.hasContent())
            throw new OrderNotFoundException("Orders Not Found");
        else
            return pagedResult.toList();

    }

    @Override
    @Transactional
    public Orders getOrderById(String id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("Order with " + id + "Not Found");
        return order.get();
    }

    @Override
    @Transactional
    public List<Orders> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        List<Orders> ordersList = orderRepository.findOrdersByCreatedAtBetween(startTime, endTime);

        return ordersList;
    }

    @Override
    @Transactional
    public List<Orders> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Orders> ordersList = orderRepository.findTop10OrdersWithHighestDollarAmountInZip(zip);
        return ordersList;
    }

    @Override
    @Transactional
    public Orders placeOrder(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    @Transactional
    public Orders cancelOrder(String id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("Order with " + id + "Not Found");
        order.get().setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order.get());
    }

    @Override
    @Transactional
    public Orders updateOrder(Orders orders, String id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("Order with " + id + "Not Found");
        order.get().setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order.get());
    }
}
