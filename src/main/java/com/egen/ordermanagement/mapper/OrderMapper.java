package com.egen.ordermanagement.mapper;

import com.egen.ordermanagement.dto.OrderDTO;
import com.egen.ordermanagement.model.entity.CustomerOrder;

public class OrderMapper {

    public CustomerOrder convertToOrderEntity(OrderDTO orderDTO) {

        CustomerOrder order = new CustomerOrder();
        order.setOrderId(orderDTO.getOrderId());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setModificationDate(orderDTO.getModificationDate());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setTotal(orderDTO.getTotal());
        order.setSubtotal(orderDTO.getSubtotal());
        order.setTax(orderDTO.getTax());
        order.setStatus(orderDTO.getStatus());
        order.setItems(orderDTO.getItems());
        order.setPayments(orderDTO.getPayments());
        order.setShipping(orderDTO.getShipping());
        return order;
    }

    public OrderDTO convertToOrderDTO(CustomerOrder order) {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCreationDate(order.getCreationDate());
        orderDTO.setModificationDate(order.getModificationDate());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setSubtotal(order.getSubtotal());
        orderDTO.setTax(order.getTax());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setItems(order.getItems());
        orderDTO.setPayments(order.getPayments());
        orderDTO.setShipping(order.getShipping());
        return orderDTO;
    }
}
