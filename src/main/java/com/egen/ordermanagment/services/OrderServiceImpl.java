package com.egen.ordermanagment.services;

import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.dtoModelMapper.OrderDTOMapper;
import com.egen.ordermanagment.exception.OrderServiceException;
import com.egen.ordermanagment.model.*;
import com.egen.ordermanagment.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
                return new OrderDTOMapper().entityToDTO(pageResult.toList());
            }
        } catch (OrderServiceException e) {
            throw new OrderServiceException("Error while retrieving all orders");
        }
    }

    @Override
    public OrdersDTO findOne(String id) {
        try {
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
            return new OrderDTOMapper().entityToDTO(orders);

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
            return new OrderDTOMapper().entityToDTO(ordersWithMaxAmount);
        }
        catch(OrderServiceException e){
            throw new OrderServiceException("Error while retrieving orders");
        }
    }

//    @Override
//    public OrdersDTO placeOrder(OrdersDTO ordersDTO) {
//        try {
//            Orders orders = new OrderDTOMapper().DTOToEntity(ordersDTO);
//            return new OrderDTOMapper().entityToDTO(orderRepository.save(orders));
//        }
//        catch (OrderServiceException e){
//            throw new OrderServiceException("Error while creating orders");
//        }
//    }

    @Override
    public OrdersDTO updateOrder(String id, OrdersDTO ordersDTO) {
        try {
            Orders orders = new OrderDTOMapper().DTOToEntity(ordersDTO);
            Optional<Orders> existing = orderRepository.findById(id);
            if(!existing.isPresent()){
                throw new OrderServiceException("No order found");
            }
            return new OrderDTOMapper().entityToDTO(orderRepository.save(orders));

        } catch (OrderServiceException ose) {
            throw new OrderServiceException("Error while updating order");
        }

    }

    @Override
    public OrdersDTO cancelOrder(String id) {
        try {
            Optional<Orders> existing = orderRepository.findById(id);
            if(!existing.isPresent()){
                // ResourceNotFoundException
                throw new OrderServiceException("No order found");
            }
            existing.get().setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(existing.get());
            return new OrderDTOMapper().entityToDTO(existing.get());

        } catch (OrderServiceException e) {
            throw new OrderServiceException("Error while updating order status as cancelled");
        }
    }

    @Override
    public Boolean placeOrder(OrdersDTO ordersDTO) {
        try {
            Orders orders = new OrderDTOMapper().DTOToEntity(ordersDTO);
            new OrderDTOMapper().entityToDTO(orderRepository.save(orders));
            return Boolean.TRUE;
        }
        catch (OrderServiceException e){
            throw new OrderServiceException("Error while creating orders");
        }
    }

}

