package com.egen.ordermanagment.dtoModelMapper;
import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.model.Orders;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper {
    public OrdersDTO entityToDTO(Orders orders){
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setId(orders.getId());
        ordersDTO.setCustomerId((orders.getCustomerId()));
        ordersDTO.setOrderSubTotal(orders.getOrderSubTotal());
        ordersDTO.setOrderTotal(orders.getOrderTotal());
        ordersDTO.setOrderTax(orders.getOrderTax());
        ordersDTO.setOrderCreated(orders.getOrderCreated());
        ordersDTO.setOrderModified(orders.getOrderModified());
        ordersDTO.setOrderStatus(orders.getOrderStatus());
        ordersDTO.setShipmentType(orders.getShipmentType());
        ordersDTO.setOrderItemsList(orders.getOrderItemsList());
        ordersDTO.setPaymentDetail(orders.getPaymentDetail());
        ordersDTO.setShippingAddress(orders.getShippingAddress());
        return ordersDTO;

    }

    public List<OrdersDTO> entityToDTO(List<Orders> orders){
        return	orders.stream().map(ord -> entityToDTO(ord)).collect(Collectors.toList());


    }

    public Orders DTOToEntity(OrdersDTO ordersDTO){
        Orders orders = new Orders();
        orders.setId(ordersDTO.getId());
        orders.setCustomerId((ordersDTO.getCustomerId()));
        orders.setOrderSubTotal(ordersDTO.getOrderSubTotal());
        orders.setOrderTotal(ordersDTO.getOrderTotal());
        orders.setOrderTax(ordersDTO.getOrderTax());
        orders.setOrderCreated(ordersDTO.getOrderCreated());
        orders.setOrderModified(ordersDTO.getOrderModified());
        orders.setOrderStatus(ordersDTO.getOrderStatus());
        orders.setShipmentType(ordersDTO.getShipmentType());
        orders.setOrderItemsList(ordersDTO.getOrderItemsList());
        orders.setPaymentDetail(ordersDTO.getPaymentDetail());
        orders.setShippingAddress(ordersDTO.getShippingAddress());
        return orders;
    }

    public List<Orders> DTOToEntity(List<OrdersDTO> ordersDto){
        return ordersDto.stream().map(ord -> DTOToEntity(ord)).collect(Collectors.toList());
    }
}
