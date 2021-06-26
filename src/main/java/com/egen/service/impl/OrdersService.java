package com.egen.service.impl;

import com.egen.dtos.ordersDTO;
import com.egen.exceptions.orderNotFoundException;
import com.egen.entity.orders;
import com.egen.repository.IOrderRepository;
import com.egen.service.IOrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private IOrderRepository orderRepo;





    private ordersDTO convertEntitytoDTO(orders orderItem) {
        ordersDTO orderItemDto = new ordersDTO();
        BeanUtils.copyProperties(orderItem, orderItemDto);
        return orderItemDto;
    }
    private orders convertDTOtoEntity(ordersDTO orderItemDTO){
        orders orderItem = new orders();
        BeanUtils.copyProperties(orderItemDTO,orderItem);


        return orderItem;

    }


    @Override
    @Transactional(readOnly = true)
    public List<orders> getAllOrders() {
        List<orders> ord = (List<orders>) orderRepo.findAll();


        return ord;
    }

    @Override
    @Transactional(readOnly = true)
    public orders getOrderById(String id) {
        Optional<orders> ord=orderRepo.findById(id);
        if(!ord.isPresent()){
            throw new orderNotFoundException("Order with the id "+id+"is not present");
        }
        return ord.get();
    }

    @Override
    @Transactional
    public ordersDTO createOrder(ordersDTO ordersdto){

        try{
            System.out.println("Inside the Place Order function");
            orders orderItem =  convertDTOtoEntity(ordersdto);
            orders responseEntity = orderRepo.save(orderItem);
            ordersdto= convertEntitytoDTO(responseEntity);
        }catch (Exception ex){
            throw new orderNotFoundException("");
        }

        return ordersdto;
    }

    @Override
    @Transactional
    public orders updateOrder(orders ord) {
        Optional<orders> ord1 = orderRepo.findById(ord.getId());
        if(!ord1.isPresent()){
            throw new orderNotFoundException("Order with the id "+ord.getId()+"is not present");
        }

        return orderRepo.save(ord);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<orders> ord = orderRepo.findById(id);
        if(!ord.isPresent()){
            throw new orderNotFoundException("Order with the id "+id+"is not present");
        }
        orderRepo.delete(ord.get());
    }
}
