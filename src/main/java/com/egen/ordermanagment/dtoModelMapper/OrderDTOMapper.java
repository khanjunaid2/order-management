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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDTOMapper {
    public OrdersDTO entityToDTO(Orders orders){
        OrdersDTO ordersDTO = new OrdersDTO();
//        BeanUtils.copyProperties(ordersDTO , orders);
        PaymentDTO paymentDTO = new PaymentDTO();
        List<OrderItemsDTO> orderItemsDTOList = new ArrayList<>();
        AddressDTO shippingAddressDTO = new AddressDTO();
        AddressDTO billingAddressDTO = new AddressDTO();

        for(int i=0; i< orders.getOrderItemsList().size(); i++){
            OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
            BeanUtils.copyProperties(orders.getOrderItemsList().get(i) , orderItemsDTO);
//            orderItemsDTO.setOrders(ordersDTO);
            orderItemsDTOList.add(orderItemsDTO);
        }
        BeanUtils.copyProperties(orders.getPaymentDetail() , paymentDTO);
        BeanUtils.copyProperties(orders.getPaymentDetail().getBillingAddress(), billingAddressDTO);
//        paymentDTO.setOrders(ordersDTO);
        paymentDTO.setBillingAddress(billingAddressDTO);

//        shippingAddressDTO.setOrders(ordersDTO);
        BeanUtils.copyProperties(orders.getShippingAddress(), shippingAddressDTO);

        ordersDTO.setId(orders.getId());
        ordersDTO.setCustomerId((orders.getCustomerId()));
        ordersDTO.setOrderSubTotal(orders.getOrderSubTotal());
        ordersDTO.setOrderTotal(orders.getOrderTotal());
        ordersDTO.setOrderTax(orders.getOrderTax());
        ordersDTO.setOrderCreated(orders.getOrderCreated());
        ordersDTO.setOrderModified(orders.getOrderModified());
        ordersDTO.setOrderStatus(orders.getOrderStatus());
        ordersDTO.setShipmentType(orders.getShipmentType());
        ordersDTO.setOrderItemsList(orderItemsDTOList);
        ordersDTO.setPaymentDetail(paymentDTO);
        ordersDTO.setShippingAddress(shippingAddressDTO);
        return ordersDTO;

    }

    public List<OrdersDTO> entityToDTO(List<Orders> orders){
        return	orders.stream().map(ord -> entityToDTO(ord)).collect(Collectors.toList());
    }

    public Orders DTOToEntity(OrdersDTO ordersDTO){
        Orders orders = new Orders();
//        BeanUtils.copyProperties(ordersDTO , orders);
        Payment payment = new Payment();
        List<OrderItems> orderItemsList = new ArrayList<>();
        Address shippingAddress = new Address();
        Address billingAddress = new Address();
        OrderItems orderItems = new OrderItems();

        for(int i=0; i< ordersDTO.getOrderItemsList().size(); i++){
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
        return orders;
    }

    public List<Orders> DTOToEntity(List<OrdersDTO> ordersDto){
        return ordersDto.stream().map(ord -> DTOToEntity(ord)).collect(Collectors.toList());
    }
}
