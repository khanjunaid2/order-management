package com.egen.mapper;

import com.egen.dto.OrderDTO;
import com.egen.model.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toOderDTO(Orders order);
    Orders ToOrder(OrderDTO orderDTO);
    List<OrderDTO> toOrderDTOs(List<Orders> orders);
    List<Orders> toOrders(List<OrderDTO> orderDTOs);
}
