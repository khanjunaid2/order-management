package com.example.ordermanagement.models;

import com.example.ordermanagement.enums.DeliveryMethod;
import com.example.ordermanagement.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private  String orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //private String customerId;

    private double subTotal;

    private double tax;

    private double totalAmount;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Item> items;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Payment> paymentDetails;

    private Timestamp createdAt;
    private Timestamp orderModified;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Orders() {
        this.orderId = UUID.randomUUID().toString();
    }
}
