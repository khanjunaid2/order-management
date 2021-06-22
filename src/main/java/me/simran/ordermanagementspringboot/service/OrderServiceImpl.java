package me.simran.ordermanagementspringboot.service;

import me.simran.ordermanagementspringboot.dto.OrderDTO;
import me.simran.ordermanagementspringboot.entity.*;
import me.simran.ordermanagementspringboot.exception.OrderServiceException;
import me.simran.ordermanagementspringboot.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<Order> getAllOrders(OrderPage orderPage) {
        try{
            Sort sort = Sort.by(orderPage.getSortDirection(), orderPage.getSortBy());
            Pageable pageable = PageRequest.of(orderPage.getPageNumber(), orderPage.getPageSize(), sort);
            return orderRepository.findAll(pageable);
        }catch(Exception e){
            throw new OrderServiceException("Order Not Found", e);
        }
    }

    @Override
    public Boolean createOrder(OrderDTO orderDto) {
        try {
            Order orderEntity = new Order();
            BeanUtils.copyProperties(orderDto, orderEntity);

            Address shipping = new Address();
            Address billing = new Address();
            Payment payment = new Payment();
            List<Item> items = new ArrayList<>(orderDto.getItems().size());

            for (int i = 0; i < orderDto.getItems().size(); i++) {
                Item item = new Item();
                BeanUtils.copyProperties(orderDto.getItems().get(i), item);
                item.setOrder(orderEntity);
                items.add(item);
            }

            orderEntity.setItems(items);
            BeanUtils.copyProperties(orderDto.getShippingAddress(), shipping);
            BeanUtils.copyProperties(orderDto.getBillingAddress(), billing);
            BeanUtils.copyProperties(orderDto.getPaymentDetails(), payment);

            shipping.setOrder(orderEntity);
            billing.setOrder(orderEntity);
            payment.setOrder(orderEntity);
            orderEntity.setPaymentDetails(payment);
            orderEntity.setBillingAddress(billing);
            orderEntity.setShippingAddress(shipping);

            orderRepository.save(orderEntity);
        }catch(Exception e) {
            throw new OrderServiceException("Error in Order saving", e);
        }
         return true;
    }


    @Override
    public Boolean createOrders(List<OrderDTO> orderDto) {
        try{
            List<Order> orderEntityList = new ArrayList<>(orderDto.size());

            for(int i=0; i<orderDto.size(); i++){
                Order orderEntity = new Order();
                BeanUtils.copyProperties(orderDto.get(i), orderEntity);

                Address shipping = new Address();
                Address billing = new Address();
                Payment payment = new Payment();
                List<Item> items = new ArrayList<>(orderDto.get(i).getItems().size());

                for (int j = 0; j < orderDto.get(i).getItems().size(); j++) {
                    Item item = new Item();
                    BeanUtils.copyProperties(orderDto.get(i).getItems().get(j), item);
                    item.setOrder(orderEntity);
                    items.add(item);
                }

                orderEntity.setItems(items);
                BeanUtils.copyProperties(orderDto.get(i).getShippingAddress(), shipping);
                BeanUtils.copyProperties(orderDto.get(i).getBillingAddress(), billing);
                BeanUtils.copyProperties(orderDto.get(i).getPaymentDetails(), payment);

                shipping.setOrder(orderEntity);
                billing.setOrder(orderEntity);
                payment.setOrder(orderEntity);
                orderEntity.setPaymentDetails(payment);
                orderEntity.setBillingAddress(billing);
                orderEntity.setShippingAddress(shipping);
                orderEntityList.add(orderEntity);
            }
            orderRepository.saveAll(orderEntityList);
        }catch(Exception e){
            throw new OrderServiceException("Exception in saving all orders", e);
        }
        return true;
    }

    @Override
    public Order getOrderById(Long id) {
        try{
            return orderRepository.getById(id);
        }catch(Exception e){
            throw new OrderServiceException("Order with id: "+id+" not found",e);
        }
    }

    @Override
    public Boolean update(OrderDTO orderDto, Long id) {
        try{
            Order orderEntity = orderRepository.getById(id);
            BeanUtils.copyProperties(orderDto, orderEntity);

            Address shipping = new Address();
            Address billing = new Address();
            Payment payment = new Payment();
            List<Item> items = new ArrayList<>(orderDto.getItems().size());

            for (int i = 0; i < orderDto.getItems().size(); i++) {
                Item item = new Item();
                BeanUtils.copyProperties(orderDto.getItems().get(i), item);
                item.setOrder(orderEntity);
                items.add(item);
            }

            orderEntity.setItems(items);
            BeanUtils.copyProperties(orderDto.getShippingAddress(), shipping);
            BeanUtils.copyProperties(orderDto.getBillingAddress(), billing);
            BeanUtils.copyProperties(orderDto.getPaymentDetails(), payment);

            shipping.setOrder(orderEntity);
            billing.setOrder(orderEntity);
            payment.setOrder(orderEntity);
            orderEntity.setPaymentDetails(payment);
            orderEntity.setBillingAddress(billing);
            orderEntity.setShippingAddress(shipping);

            orderRepository.save(orderEntity);
        }catch(Exception e) {
            throw new OrderServiceException("Error in Order saving", e);
        }
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        try{
            Order orderEntity = orderRepository.getById(id);
            orderRepository.delete(orderEntity);
        }catch(Exception e){
            throw new OrderServiceException("Order with id: "+id+" cannot be deleted", e);
        }
        return true;
    }
}
