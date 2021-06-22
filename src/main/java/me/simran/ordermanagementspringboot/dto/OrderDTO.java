package me.simran.ordermanagementspringboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import me.simran.ordermanagementspringboot.enums.DeliveryType;
import me.simran.ordermanagementspringboot.enums.OrderStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Long id;

    private OrderStatus orderStatus;

    private String customerId;

    private double subTotal;

    private double tax;

    private double totalAmount;

    private AddressDTO shippingAddress;

    private AddressDTO billingAddress;

    private boolean isBillingAddressSame;

    private DeliveryType deliveryType;

    private List<ItemDTO> items;

    private PaymentDTO paymentDetails;

}
