package com.egen.dto;

import com.egen.enums.OrderStatus;
import com.egen.enums.ShipmentMethods;
import com.egen.model.Address;
import com.egen.model.Customer;
import com.egen.model.Item;
import com.egen.model.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
    private String id;
    private Timestamp createdDate;
    private boolean isBillingAddressSame;
    private Double total;
    private Double subtotal;
    private Double tax;
    private OrderStatus orderStatus;
    private ShipmentMethods deliveryType;
    private Customer customer;
    private List<Item> items = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private Address shippingAddress;
}