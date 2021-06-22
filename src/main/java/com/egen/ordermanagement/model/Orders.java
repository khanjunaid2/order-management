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

    public Orders setId(Long id) {
        this.id = id;
        return this;
    }

    public Orders setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Orders setDateOrdered(Timestamp dateOrdered) {
        this.dateOrdered = dateOrdered;
        return this;
    }

    public Orders setExpectedDelivery(Timestamp expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
        return this;
    }

    public Orders setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
        return this;
    }

    public Orders setSubTotal(double subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public Orders setTax(double tax) {
        this.tax = tax;
        return this;
    }

    public Orders setShippingCharges(double shippingCharges) {
        this.shippingCharges = shippingCharges;
        return this;
    }

    public Orders setTotal(double total) {
        this.total = total;
        return this;
    }

    public Orders setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Orders setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
        return this;
    }

    public Orders setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public Orders setItems(Set<Item> items) {
        this.items = items;
        return this;
    }

    public Orders setPaymentDetails(Set<Payment> paymentDetails) {
        this.paymentDetails = paymentDetails;
        return this;
    }
}
