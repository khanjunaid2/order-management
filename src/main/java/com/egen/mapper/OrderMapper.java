package com.egen.mapper;

import com.egen.dto.OrderDTO;
import com.egen.model.Order;


import java.util.List;


public interface OrderMapper {
    Order toOderDTO(Order order);
    Order ToOrder(OrderDTO orderDTO);
    List<OrderDTO> toOrderDTOs(List<Order> orders);
    List<Order> toOrders(List<OrderDTO> orderDTOs);
}
