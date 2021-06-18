package com.egen.ordermanagement.model;

import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.enums.ShipmentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name = "cust_id")
    private Long customerId;

    private Timestamp dateOrdered;

    private Timestamp expectedDelivery;

    private int itemQuantity;

    private double subTotal;

    private double tax;

    private double shippingCharges;

    private double total;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private ShipmentMethod shipmentMethod;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Address shippingAddress;

    @OneToMany(mappedBy = "orders",targetEntity = Item.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Item> items = new HashSet<Item>();

    @OneToMany(mappedBy = "orders",targetEntity = Payment.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Payment> paymentDetails = new HashSet<Payment>();

    public Orders(Long customerId, Timestamp dateOrdered, Timestamp expectedDelivery, int itemQuantity, double subTotal,
                  double tax, double shippingCharges, double total,
                  OrderStatus orderStatus, ShipmentMethod shipmentMethod, Address shippingAddress) {
        this.customerId = customerId;
        this.dateOrdered = dateOrdered;
        this.expectedDelivery = expectedDelivery;
        this.itemQuantity = itemQuantity;
        this.subTotal = subTotal;
        this.tax = tax;
        this.shippingCharges = shippingCharges;
        this.total = total;
        this.orderStatus = orderStatus;
        this.shipmentMethod = shipmentMethod;
        this.shippingAddress = shippingAddress;
    }
}
