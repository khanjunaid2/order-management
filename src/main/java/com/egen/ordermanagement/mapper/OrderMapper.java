package com.egen.ordermanagement.mapper;

import com.egen.ordermanagement.dto.*;
import com.egen.ordermanagement.model.entity.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

public class OrderMapper {

    public CustomerOrder convertToOrderEntity(OrderDTO orderDTO) {

        Item item = new Item();
        Payment payment = new Payment();
        Shipping shipping = new Shipping();
        Billing billing = new Billing();

        ArrayList<Item> itemList =  new ArrayList<>();
        ArrayList<Payment> paymentList =  new ArrayList<>();

        orderDTO.getItems().forEach(itemDTO -> {
            BeanUtils.copyProperties(itemDTO, item);
            itemList.add(item);
        });

        orderDTO.getPayments().forEach(paymentDTO -> {
            BeanUtils.copyProperties(paymentDTO, payment);
            BeanUtils.copyProperties(paymentDTO.getBilling(), billing);
            payment.setBilling(billing);
            paymentList.add(payment);
        });

        BeanUtils.copyProperties(orderDTO.getShipping(), shipping);

        return new CustomerOrder().setOrderId(orderDTO.getOrderId())
                                   .setCreationDate(orderDTO.getCreationDate())
                                   .setModificationDate(orderDTO.getModificationDate())
                                   .setCustomerId(orderDTO.getCustomerId())
                                   .setTotal(orderDTO.getTotal())
                                   .setSubtotal(orderDTO.getSubtotal())
                                   .setTax(orderDTO.getTax())
                                   .setStatus(orderDTO.getStatus())
                                   .setItems(itemList)
                                   .setPayments(paymentList)
                                   .setShipping(shipping);
    }

    public OrderDTO convertToOrderDTO(CustomerOrder order) {

        ItemDTO itemDTO = new ItemDTO();
        PaymentDTO paymentDTO = new PaymentDTO();
        ShippingDTO shippingDTO = new ShippingDTO();
        BillingDTO billingDTO = new BillingDTO();

        ArrayList<ItemDTO> itemDTOList =  new ArrayList<>();
        ArrayList<PaymentDTO> paymentDTOList =  new ArrayList<>();

        order.getItems().forEach(item -> {
            BeanUtils.copyProperties(item, itemDTO);
            itemDTOList.add(itemDTO);
        });

        order.getPayments().forEach(payment -> {
            BeanUtils.copyProperties(payment, paymentDTO);
            BeanUtils.copyProperties(payment.getBilling(), billingDTO);
            paymentDTO.setBilling(billingDTO);
            paymentDTOList.add(paymentDTO);
        });

        BeanUtils.copyProperties(order.getShipping(), shippingDTO);

            return new OrderDTO().setOrderId(order.getOrderId())
                                     .setCreationDate(order.getCreationDate())
                                     .setModificationDate(order.getModificationDate())
                                     .setCustomerId(order.getCustomerId())
                                     .setTotal(order.getTotal())
                                     .setSubtotal(order.getSubtotal())
                                     .setTax(order.getTax())
                                     .setStatus(order.getStatus())
                                     .setItems(itemDTOList)
                                     .setPayments(paymentDTOList)
                                     .setShipping(shippingDTO);
    }
}
