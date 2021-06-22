package com.example.ordermanagement.mappers;

import com.example.ordermanagement.DTO.OrdersDto;
import com.example.ordermanagement.models.Orders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdersMappers {
    @Autowired
    ModelMapper modelMapper;

    //Converts dto to entity
    public Orders mapToEntity(OrdersDto ordersDto){
        return modelMapper.map(ordersDto, Orders.class);
    }

    //Converts entity to dto
    public OrdersDto mapToDto(Orders orders){
        return modelMapper.map(orders, OrdersDto.class);
    }
}
