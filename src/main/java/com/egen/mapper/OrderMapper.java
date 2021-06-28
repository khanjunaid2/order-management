package com.egen.mapper;

import com.egen.dto.OrderDto;
import com.egen.model.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * MapStruct automatically does mapping
 * from the interface we created here
 * in resource folder
 */
@Mapper(componentModel = "spring", uses = {PaymentMapper.class, ItemMapper.class, ShippingMapper.class })
public interface OrderMapper {
    OrderDto toOderDTO(Orders order);
    Orders ToOrder(OrderDto orderDTO);
    List<OrderDto> toOrderDTOs(List<Orders> orders);
    List<Orders> toOrders(List<OrderDto> orderDTOs);
}
