package com.egen.model;

import com.egen.model.enums.OrderStatus;
import com.egen.model.enums.ShipmentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Order {

    @Id
    private String id;

    private String orderId;

    private String customerId;

    private double subTotal;

    private double tax;

    private double totalAmount;

    private ZonedDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<OrderItems> orderItems;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<OrderPayment> orderPayments;

    @OneToOne(cascade = {CascadeType.ALL})
    private Customer customer;


}
