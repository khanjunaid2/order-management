package com.egen.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "Orders.findAll",query = "SELECT ord from Orders ord"),
        @NamedQuery(name = "Orders.findOne",query = "SELECT ord from Orders ord WHERE ord.id=:paramOrderId")
})
@Table(name = "Orders")
public class Order {

    @Id
    private String id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "date_ordered")
    private Timestamp dateOrdered;

    @Column(name = "expected_delivery")
    private Timestamp expectedDelivery;

    @Column(name = "item_quantity")
    private int itemQuantity;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "tax")
    private double tax;

    @Column(name = "shipping_charges")
    private double shippingCharges;

    @Column(name = "total")
    private double total;

    @Enumerated(EnumType.STRING)
    private OrderEnum orderStatus;

    @Column(name = "shipment_method")
    @Enumerated(EnumType.STRING)
    private ShipEnum shipmentMethod;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ship_id")
    private Address shippingAddress;

    @OneToMany(mappedBy = "orders", targetEntity = Items.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Items> items = new HashSet<Items>();

    @OneToMany(mappedBy = "orders", targetEntity = Payments.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Payments> paymentDetails = new HashSet<Payments>();

    public Order(){
        this.id = UUID.randomUUID().toString();
    }

    public Order(Long customerId, Timestamp dateOrdered, Timestamp expectedDelivery, int itemQuantity, double subTotal, double tax, double shippingCharges, double total, OrderEnum orderStatus, ShipEnum shipmentMethod, Address shippingAddress, Set<Items> items, Set<Payments> paymentDetails) {
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
        this.items = items;
        this.paymentDetails = paymentDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Timestamp getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Timestamp dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Timestamp getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(Timestamp expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShipEnum getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipEnum shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<Items> getItems() {
        return items;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
    }

    public Set<Payments> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<Payments> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}