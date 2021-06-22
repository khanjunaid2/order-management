package com.egen.dto;

import com.egen.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private long id;

    private OrderStatus status;

    public Customer customer;

    public Set<Items> items = new HashSet<>();

    private double subtotal;

    private double tax;

    private double shippingCharges;

    private double total;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    public Set<Payment> payment = new HashSet<>();;

    private ShippingMethod shippingMethod;

    public Address shippingAddress;

}
