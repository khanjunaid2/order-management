package com.egen.mapper;

import com.egen.dto.OrderDTO;
import com.egen.model.Order;

public class OrderMapper {

    public OrderDTO setOrderDtoObject(Order ord) {
        OrderDTO obj = new OrderDTO();
        obj.setCustomer(ord.getCustomer());
        obj.setId(ord.getId());
        obj.setItems(ord.getItems());
        obj.setCreatedDate(ord.getCreatedDate());
        obj.setPayment(ord.getPayment());
        obj.setModifiedDate(ord.getModifiedDate());
        obj.setShippingAddress(ord.getShippingAddress());
        obj.setStatus(ord.getStatus());
        obj.setShippingCharges(ord.getShippingCharges());
        obj.setSubtotal(ord.getSubtotal());
        obj.setTax(ord.getTax());
        obj.setTotal(ord.getTotal());
        obj.setShippingMethod(ord.getShippingMethod());
        return obj;
    }
    public Order setOrderObject(OrderDTO ord) {
        Order obj = new Order();
        obj.setCustomer(ord.getCustomer());
        obj.setId(ord.getId());
        obj.setItems(ord.getItems());
        obj.setCreatedDate(ord.getCreatedDate());
        obj.setPayment(ord.getPayment());
        obj.setModifiedDate(ord.getModifiedDate());
        obj.setShippingAddress(ord.getShippingAddress());
        obj.setStatus(ord.getStatus());
        obj.setShippingCharges(ord.getShippingCharges());
        obj.setSubtotal(ord.getSubtotal());
        obj.setTax(ord.getTax());
        obj.setTotal(ord.getTotal());
        obj.setShippingMethod(ord.getShippingMethod());
        return obj;
    }
}
