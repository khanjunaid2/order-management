package com.egen.ordermanagement.service.impl;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.exception.OrderRequestProcessException;
import com.egen.ordermanagement.exception.OrderServiceException;
import com.egen.ordermanagement.mapper.OrderMapper;
import com.egen.ordermanagement.model.entity.CustomerOrder;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.egen.ordermanagement.repository.OrderRepository;
import com.egen.ordermanagement.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
   public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {

        try {
            return convertToDTOList(orderRepository.findAll());

           } catch (Exception e) {

            log.error("Error while fetching all orders");
            throw new OrderServiceException("Failed to fetch all orders", e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersWithPaginationAndSorted(int pageNumber, int pageSize, String sortBy) {

        try {
            Page<CustomerOrder> pagedOrders = orderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("creationDate")));

            if (pagedOrders.hasContent())
                return convertToDTOList(pagedOrders.getContent());
            else
                throw new OrderRequestProcessException("No orders were placed on the given date");

        } catch (Exception e) {

            log.error("Error while retrieving all orders with pagination and sorting");
            throw new OrderServiceException("Failed to retrieve all orders with pagination and sorting", e);
        }
    }

    @Override
    public OrderDTO getOrderById(String id) {

        try {
            Optional<CustomerOrder> order = orderRepository.findById(id);

            if(order.isEmpty())
                throw new OrderRequestProcessException("Order" + id + " you're looking for couldn't be found");

            return new OrderMapper().convertToOrderDTO(order.get());

        } catch (Exception e) {
           log.error("Error while fetching specific order");
            throw new OrderServiceException("Failed to fetch specific order", e);
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

        try {
            return convertToDTOList(orderRepository.findAllOrdersWithInInterval(startTime, endTime));

        } catch (Exception e) {
            log.error("Error while fetching all orders within the given interval");
            throw new OrderServiceException("Failed to fetch all orders within the given interval", e);
        }
    }

    @Override
    public List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip) {

        try {
            List<CustomerOrder> allOrders = orderRepository.findAll();

            if(allOrders.size() == 0)
                throw new OrderRequestProcessException("No orders have been placed yet at a given location.");

            return convertToDTOList(allOrders.stream()
                                              .filter(order -> order.getShipping().getZip().equals(zip))
                                              .sorted((o1, o2) -> Double.compare(o2.getTotal(), o1.getTotal()))
                                              .limit(10)
                                              .collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("Error while fetching top 10 orders in a given location");
            throw new OrderServiceException("Failed to fetch top 10 orders", e);
        }
    }

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {

        try {
            CustomerOrder order = new OrderMapper().convertToOrderEntity(orderDTO);
            return new OrderMapper().convertToOrderDTO(orderRepository.save(order));

        } catch (Exception e) {
            log.error("Error while order creation");
            throw new OrderServiceException("Failed order creation", e);
        }
    }

    @Override
    public OrderDTO cancelOrder(String orderId) {

        try {
             Optional<CustomerOrder> order = orderRepository.findById(orderId);

             if(order.isEmpty())
                 throw new OrderRequestProcessException("Order doesn't exist");

             else if(order.get().getStatus().equals(OrderStatus.SHIPPED))
                 throw new OrderRequestProcessException("shipped orders can't be cancelled");

             else {
                 order.get().setStatus(OrderStatus.CANCELLED);
                 orderRepository.save(order.get());
                 return new OrderMapper().convertToOrderDTO(order.get());
             }

        } catch (Exception e) {
            log.error("Error while order creation");
            throw new OrderServiceException("Failed order cancellation", e);
        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {

        try {
            CustomerOrder order = new OrderMapper().convertToOrderEntity(orderDTO);
            Optional<CustomerOrder> existingOrder = orderRepository.findById(order.getOrderId());

            if(existingOrder.isEmpty())
                throw new OrderServiceException("Order doesn't exist");

            else if (existingOrder.get().getStatus().equals(OrderStatus.SHIPPED))
                throw new OrderServiceException("Shipped orders can't be modified");

            else
                return new OrderMapper().convertToOrderDTO(orderRepository.save(order));

        } catch (Exception e) {
            log.error("Error while order modification");
            throw new OrderServiceException("Failed order modification", e);
        }
    }

    private List<OrderDTO> convertToDTOList(List<CustomerOrder> orders) {

        return orders.stream()
                     .map(order -> new OrderMapper().convertToOrderDTO(order))
                     .collect(Collectors.toList());
    }
}