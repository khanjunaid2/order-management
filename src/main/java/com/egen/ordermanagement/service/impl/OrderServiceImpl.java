package com.egen.ordermanagement.service.impl;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.exception.InternalServerException;
import com.egen.ordermanagement.exception.OrderServiceException;
import com.egen.ordermanagement.mapper.OrderMapper;
import com.egen.ordermanagement.model.entity.CustomerOrder;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.egen.ordermanagement.repository.OrderRepository;
import com.egen.ordermanagement.service.OrderService;
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

           } catch (InternalServerException ise) {

            System.out.println("Failed while fetching all orders");
            throw new InternalServerException("Internal Server Error occurred");
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersWithPaginationAndSorted(int pageNumber, int pageSize, String sortBy) {

        try {
            Page<CustomerOrder> pagedOrders = orderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("creationDate")));

            if (pagedOrders.hasContent())
                return convertToDTOList(pagedOrders.getContent());
            else
                throw new OrderServiceException("No orders were placed on the given date");

        } catch (OrderServiceException ose) {
            throw new OrderServiceException("Error while retrieving all orders with pagination and sorting");
        }
    }


    @Override
    public OrderDTO getOrderById(String id) {

        try {
            Optional<CustomerOrder> order = orderRepository.findById(id);
            if(order.isEmpty())
                throw new OrderServiceException("Order you're looking for couldn't be found");
            return new OrderMapper().convertToOrderDTO(order.get());

        } catch (InternalServerException ise) {
            System.out.println("Failed while fetching specific order");
            throw new InternalServerException("Internal Server Error occurred");
        }
    }

    @Override
    public List<OrderDTO> getAllOrdersWithInInterval(Timestamp startTime, Timestamp endTime) {

        try {
            return convertToDTOList(orderRepository.findAllOrdersWithInInterval(startTime, endTime));

        } catch (InternalServerException ise) {
            System.out.println("Failed while fetching all orders within the given interval");
            throw new InternalServerException("Internal Server Error occurred");
        }
    }

    @Override
    public List<OrderDTO> top10OrdersWithHighestDollarAmountInZip(String zip) {

        try {
            List<CustomerOrder> allOrders = orderRepository.findAll();

            if(allOrders.size() == 0)
                throw new OrderServiceException("No orders have been placed yet at a given location.");

            return convertToDTOList(allOrders.stream()
                                              .filter(order -> order.getShipping().getZip().equals(zip))
                                              .sorted((o1, o2) -> Double.compare(o2.getTotal(), o1.getTotal()))
                                              .limit(10)
                                              .collect(Collectors.toList()));
        }
        catch (InternalServerException ise) {
            System.out.println("Failed while fetching top 10 orders in a given location");
            throw new InternalServerException("Internal Server Error occurred");
        }
    }

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {

        try {
            CustomerOrder order = new OrderMapper().convertToOrderEntity(orderDTO);
            orderRepository.save(order);
            return new OrderMapper().convertToOrderDTO(order);

        } catch (OrderServiceException ose) {
            System.out.println("Failed while order creation");
            throw new OrderServiceException("Failed while order creation");
        }
    }

    @Override
    public OrderDTO cancelOrder(String orderId) {

        try {
             Optional<CustomerOrder> order = orderRepository.findById(orderId);

             if(order.isEmpty())
                 throw new OrderServiceException("Order could not be found");
             else {
                 order.get().setStatus(OrderStatus.CANCELLED);
                 orderRepository.save(order.get());
                 return new OrderMapper().convertToOrderDTO(order.get());
             }

        } catch (OrderServiceException ose) {
            System.out.println("Failed while order cancellation");
            throw new OrderServiceException("Failed while order cancellation");
        }
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {

        try {
            CustomerOrder order = new OrderMapper().convertToOrderEntity(orderDTO);
            Optional<CustomerOrder> existingOrder = orderRepository.findById(order.getOrderId());

            if(existingOrder.isEmpty())
                throw new OrderServiceException("Order doesn't exist");

            else if (existingOrder.get().getStatus().equals(OrderStatus.SHIPPED))   //check if order is already dispatched
                throw new OrderServiceException("The order has been shipped");
            else
                return new OrderMapper().convertToOrderDTO(orderRepository.save(order));

        } catch (OrderServiceException ose) {
            System.out.println("Failed while order modification");
            throw new OrderServiceException("Failed while order modification");
        }
    }

    private List<OrderDTO> convertToDTOList(List<CustomerOrder> orders) {

        return orders.stream()
                     .map(order -> new OrderMapper().convertToOrderDTO(order))
                     .collect(Collectors.toList());
    }
}