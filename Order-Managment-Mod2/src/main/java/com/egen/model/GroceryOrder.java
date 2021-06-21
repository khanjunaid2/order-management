package com.egen.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
public class GroceryOrder {

    @Id
    private  String id;

    private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String customerId;

    private double subTotal;

    private double tax;

    private double totalAmount;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Item> items;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Payment> paymentDetails;

    private Timestamp createdAt;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    private Customer customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Payment> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<Payment> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "GroceryOrder{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderStatus=" + orderStatus +
                ", customerId='" + customerId + '\'' +
                ", subTotal=" + subTotal +
                ", tax=" + tax +
                ", totalAmount=" + totalAmount +
                ", shippingAddress=" + shippingAddress +
                ", deliveryType=" + deliveryType +
                ", items=" + items +
                ", paymentDetails=" + paymentDetails +
                ", createdAt=" + createdAt +
                ", customer=" + customer +
                '}';
    }
}
