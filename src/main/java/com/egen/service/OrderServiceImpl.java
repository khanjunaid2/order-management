package com.egen.service;

import com.egen.dto.OrderDTO;
import com.egen.exception.OrderServiceException;
import com.egen.mapper.OrderMapper;
import com.egen.model.entity.Orders;
import com.egen.model.enums.OrderStatus;
import com.egen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    public OrderMapper orderMapper;

    @Autowired
    public OrderRepository orderRepository;

    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = orderMapper.toOrderDTOs((List<Orders>) orderRepository.findAll());
        if (orders.size() > 0) {
            return orders;
        }
        throw new OrderServiceException("No orders placed");
    }

    public List<OrderDTO> getOrderPage(int pagenumber) {
        List<OrderDTO> orders = orderMapper.toOrderDTOs(orderRepository.findAll(PageRequest.of(pagenumber, 2)).getContent());
        if (orders.size() > 0) {
            return orders;
        }
        throw new OrderServiceException("No orders");
    }

    public OrderDTO getOrderById(String eid) {
        Optional<Orders> order = orderRepository.findById(eid);
        if (!order.isPresent()) {
            throw new OrderServiceException("Order with id" + eid + "does not exist");
        }
        return orderMapper.toOderDTO(order.get());
    }

    public Boolean placeOrder(OrderDTO orderDTO) {
        Optional<Orders> ordersOptional = orderRepository.findById(orderDTO.getOrderId());
        if (ordersOptional.isPresent()) {
            throw new OrderServiceException("Order already exist");
        }
        Orders order = orderMapper.ToOrder(orderDTO);
        order.setOrderStatus(OrderStatus.PLACED);
        orderRepository.save(order);
        return Boolean.TRUE;
    }

    public List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        List<OrderDTO> orders = orderMapper.toOrderDTOs(orderRepository.getAllOrdersWithInInterval(startTime, endTime));
        if (orders.size() > 0) {
            return orders;
        }
        throw new OrderServiceException("No orders found within this period");
    }

    public List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<OrderDTO> orders = orderMapper.toOrderDTOs(orderRepository.findTop10OrdersWithHighestDollarAmountInZip(zip));
        if (orders.size() > 0) {
            return orders;
        }
        throw new OrderServiceException("No orders found of " + zip + " zipcode");
    }

    public OrderDTO cancelOrder(String id) {
        Optional<Orders> ordersOptional = orderRepository.findById(id);
        if (!ordersOptional.isPresent()) {
            throw new OrderServiceException("Order with id" + id + "does not exist");
        } else {
            Orders order = ordersOptional.get();
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
            return orderMapper.toOderDTO(order);
        }
    }

    public OrderDTO updateOrder(String id, OrderDTO orderDTO) {
        Optional<Orders> ordersOptional = orderRepository.findById(id);
        if (!ordersOptional.isPresent()) {
            throw new OrderServiceException("Order with id" + id + "does not exist");
        } else {
            Orders order = orderMapper.ToOrder(orderDTO);
            orderRepository.save(order);
            return orderMapper.toOderDTO(order);
        }
    }
}