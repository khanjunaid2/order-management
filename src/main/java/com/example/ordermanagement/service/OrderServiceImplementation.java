package com.example.ordermanagement.service;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.enums.DeliveryMethod;
import com.example.ordermanagement.enums.OrderStatus;
import com.example.ordermanagement.exceptions.OrderNotFoundException;
import com.example.ordermanagement.mappers.OrdersMappers;
import com.example.ordermanagement.models.Orders;
import com.example.ordermanagement.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImplementation implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrdersMappers ordersMappers;

    @Override
    @Transactional
    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = (List<Orders>) orderRepository.findAll();
        List<OrdersDto> ordersDtoList = new ArrayList<>();
        ordersList.forEach(orders -> {
            ordersDtoList.add(ordersMappers.mapToDto(orders));
        });
//        for(Orders order : ordersList){
//            OrdersDto ordersResponse = ordersMappers.mapToDto(order);
//            ordersDtoList.add(ordersResponse);
//        }

        return ordersDtoList;
    }

    @Override
    public List<OrdersDto> getAllOrdersByPagingAndSorting(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("totalAmount").ascending());
        Page<Orders> pagedResult = orderRepository.findAll(paging);
        if(!pagedResult.hasContent())
            throw new OrderNotFoundException("Orders Not Found");
        else{
            List<Orders> ordersList = pagedResult.toList();
            List<OrdersDto> ordersDtoList = new ArrayList<>();
            ordersList.forEach(orders -> {
                ordersDtoList.add(ordersMappers.mapToDto(orders));
            });

            return ordersDtoList;
        }
           // return pagedResult.toList();

    }

    @Override
    @Transactional
    public OrdersDto getOrderById(String id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("Order with " + id + "Not Found");
        return ordersMappers.mapToDto(order.get());
    }

    @Override
    @Transactional
    public List<OrdersDto> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {
        List<Orders> ordersList = orderRepository.findOrdersByCreatedAtBetween(startTime, endTime);

        List<OrdersDto> ordersDtoList = new ArrayList<>();
        ordersList.forEach(orders -> {
            ordersDtoList.add(ordersMappers.mapToDto(orders));
        });
        return ordersDtoList;
    }

    @Override
    @Transactional
    public List<OrdersDto> top10OrdersWithHighestDollarAmountInZip(String zip) {
        List<Orders> ordersList = orderRepository.findTop10OrdersWithHighestDollarAmountInZip(zip);

        List<OrdersDto> ordersDtoList = new ArrayList<>();
        ordersList.forEach(orders -> {
            ordersDtoList.add(ordersMappers.mapToDto(orders));
        });
        return ordersDtoList;
    }

    @Override
    @Transactional
    public OrdersDto placeOrder(OrdersDto ordersDto) {

        //Convert DTO to entity
        Orders orders = ordersMappers.mapToEntity(ordersDto);
        Orders newOrders = orderRepository.save(orders);

        //convert entity to dto
        OrdersDto ordersResponse = ordersMappers.mapToDto(newOrders);
        return ordersResponse;
    }

    @Override
    @Transactional
    public OrdersDto cancelOrder(String id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("Order with " + id + "Not Found");
        order.get().setOrderStatus(OrderStatus.CANCELED);
        return ordersMappers.mapToDto(orderRepository.save(order.get()));
    }

    @Override
    @Transactional
    public OrdersDto updateOrder(Orders orders, String id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new OrderNotFoundException("Order with " + id + "Not Found");
        order.get().setOrderStatus(OrderStatus.UPDATED);
        order.get().setDeliveryMethod(DeliveryMethod.IN_STORE_PICKUP);
        return ordersMappers.mapToDto(orderRepository.save(order.get()));
    }

}
