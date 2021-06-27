package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.dtoModelMapper.OrderDTOMapper;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public  List<Orders> getAllOrders(){
        try {
            List<Orders> result =  orderRepository.findAll();
            if(result.isEmpty()){
                throw new OrderServiceException("No orders found");
            }
            return result;
        } catch (OrderServiceException e) {
            throw new OrderServiceException("Error while retrieving all orders");
        }
    }


    @Override
    public List<OrdersDTO> findAllPaginationSorting(int page, int size, String sortBy) {
        try {
            Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
            Page<Orders> pageResult = orderRepository.findAll(paging);
            if (pageResult.toList().isEmpty()){
                throw new OrderServiceException("No orders found");
            }
            else {
                return entityToDTOList(pageResult.toList());
            }
        } catch (OrderServiceException e) {
            throw new OrderServiceException("Error while retrieving all orders");
        }
    }

    @Override
    public OrdersDTO findOne(String id) {
        try {
            System.out.println(id);
            Optional<Orders> existing = orderRepository.findById(id);
            if(!existing.isPresent()){
                throw new OrderServiceException("No order found");
            }
            return new OrderDTOMapper().entityToDTO(existing.get());
        }
        catch (OrderServiceException e){
            throw new OrderServiceException("Order with id "+id +" not found");
        }
    }

    @Override
    public List<OrdersDTO> findWithinInterval(Timestamp startTime, Timestamp endTime) {
        try {
            List<Orders> orders = orderRepository.findAllByOrderCreatedBetween(startTime,endTime);
            if(orders.isEmpty()){
                throw new OrderServiceException("No order found");
            }
            return entityToDTOList(orders);

        } catch (OrderServiceException e) {
            throw new OrderServiceException("Error while retrieving orders");
        }
    }

    @Override
    public List<OrdersDTO> findTop10OrdersWithHighestDollarAmountInZip(String zip) {
        try {
            List<Orders> ordersWithMaxAmount = orderRepository.findTop10OrdersWithHighestDollarAmountInZip(zip);
            if(ordersWithMaxAmount.isEmpty()){
                throw new OrderServiceException("No order found");
            }
            return entityToDTOList(ordersWithMaxAmount);
        }
        catch(OrderServiceException e){
            throw new OrderServiceException("Error while retrieving orders");
        }
    }


    @Override
    public Boolean updateOrder(String id, OrdersDTO ordersDTO) {
        try {
            Orders orders = new OrderDTOMapper().DTOToEntity(ordersDTO);
            Optional<Orders> existing = orderRepository.findById(id);
            if(!existing.isPresent()){
                throw new OrderServiceException("No order found");
            }
            orderRepository.save(orders);
//            return new OrderDTOMapper().entityToDTO(orders);
            return Boolean.TRUE;

        } catch (OrderServiceException ose) {
            throw new OrderServiceException("Error while updating order");
        }

    }

    @Override
    public Boolean cancelOrder(String id) {
        try {
            Optional<Orders> existing = orderRepository.findById(id);
            log.info("existing"+ existing.get());
            if(!existing.isPresent()){
                throw new OrderServiceException("No order found");
            }
            existing.get().setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(existing.get());
            return Boolean.TRUE;

        } catch (OrderServiceException e) {
            throw new OrderServiceException("Error while updating order status as cancelled");
        }
    }

    @Override
    public Boolean placeOrder(OrdersDTO ordersDTO) {
        try {
            Orders orders = new OrderDTOMapper().DTOToEntity(ordersDTO);
            orderRepository.save(orders);
//            new OrderDTOMapper().entityToDTO(orders);
            return Boolean.TRUE;
        }
        catch (OrderServiceException e){
            throw new OrderServiceException("Error while creating orders");
        }
    }


    private List<OrdersDTO> entityToDTOList(List<Orders> orders) {
        return orders.stream().map(ord -> new OrderDTOMapper().entityToDTO(ord)).collect(Collectors.toList());
    }

}

