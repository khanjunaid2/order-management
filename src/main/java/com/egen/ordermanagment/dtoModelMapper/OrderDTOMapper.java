package com.egen.ordermanagment.dtoModelMapper;
import com.egen.ordermanagment.dto.AddressDTO;
import com.egen.ordermanagment.dto.OrderItemsDTO;
import com.egen.ordermanagment.dto.OrdersDTO;
import com.egen.ordermanagment.dto.PaymentDTO;
import com.egen.ordermanagment.model.Address;
import com.egen.ordermanagment.model.OrderItems;
import com.egen.ordermanagment.model.Orders;
import com.egen.ordermanagment.model.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class OrderDTOMapper {
//    @Autowired
//    OrderDTOMapper orderDTOMapper;

    public OrdersDTO entityToDTO(Orders orders){
        OrdersDTO ordersDTO = new OrdersDTO();
        PaymentDTO paymentDTO = new PaymentDTO();
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();
        AddressDTO shippingAddressDTO = new AddressDTO();
        AddressDTO billingAddressDTO = new AddressDTO();

        for(int i=0; i< orders.getOrderItemsList().size(); i++){
            OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
            BeanUtils.copyProperties(orders.getOrderItemsList().get(i) , orderItemsDTO);
            orderItemsDTOList.add(orderItemsDTO);
        }
        BeanUtils.copyProperties(orders.getPaymentDetail() , paymentDTO);
        BeanUtils.copyProperties(orders.getPaymentDetail().getBillingAddress(), billingAddressDTO);
        paymentDTO.setBillingAddress(billingAddressDTO);

        BeanUtils.copyProperties(orders.getShippingAddress(), shippingAddressDTO);

        ordersDTO.setId(orders.getId())
                .setCustomerId((orders.getCustomerId()))
                .setOrderSubTotal(orders.getOrderSubTotal())
                .setOrderTotal(orders.getOrderTotal())
                .setOrderTax(orders.getOrderTax())
                .setOrderCreated(orders.getOrderCreated())
                .setOrderModified(orders.getOrderModified())
                .setOrderStatus(orders.getOrderStatus())
                .setShipmentType(orders.getShipmentType())
                .setOrderItemsList(orderItemsDTOList)
                .setPaymentDetail(paymentDTO)
                .setShippingAddress(shippingAddressDTO);
        return ordersDTO;
    }

//    public List<OrdersDTO> entityToDTO(List<Orders> orders){
//        return	orders.stream().map(ord -> entityToDTO(ord)).collect(Collectors.toList());
//    }

    public Orders DTOToEntity(OrdersDTO ordersDTO){
        System.out.println(ordersDTO);
        Orders orders = new Orders();
        Payment payment = new Payment();
        List<OrderItems> orderItemsList = new ArrayList<>();
        Address shippingAddress = new Address();
        Address billingAddress = new Address();

        for(int i=0; i< ordersDTO.getOrderItemsList().size(); i++){
            OrderItems orderItems = new OrderItems();
            BeanUtils.copyProperties(ordersDTO.getOrderItemsList().get(i) , orderItems);
            orderItems.setOrders(orders);
            orderItemsList.add(orderItems);
        }
        BeanUtils.copyProperties(ordersDTO.getPaymentDetail() , payment);
        BeanUtils.copyProperties(ordersDTO.getPaymentDetail().getBillingAddress(), billingAddress);
        payment.setOrders(orders);
        payment.setBillingAddress(billingAddress);

        shippingAddress.setOrders(orders);
        BeanUtils.copyProperties(ordersDTO.getShippingAddress(), shippingAddress);

        orders.setId(ordersDTO.getId())
                .setCustomerId((ordersDTO.getCustomerId()))
                .setOrderSubTotal(ordersDTO.getOrderSubTotal())
                .setOrderTotal(ordersDTO.getOrderTotal())
                .setOrderTax(ordersDTO.getOrderTax())
                .setOrderCreated(ordersDTO.getOrderCreated())
                .setOrderModified(ordersDTO.getOrderModified())
                .setOrderStatus(ordersDTO.getOrderStatus())
                .setShipmentType(ordersDTO.getShipmentType())
                .setOrderItemsList(orderItemsList)
                .setPaymentDetail(payment)
                .setShippingAddress(shippingAddress);
        System.out.println(orders);
        return orders;
    }
}
