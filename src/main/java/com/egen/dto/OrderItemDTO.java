package com.egen.dto;

import com.egen.model.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public class OrderItemDTO {

    private  String id;
    private String orderId;
    private double subTotal;
    private double tax;
    private double totalAmount;

     private OrderStatus orderStatus;

     private DeliveryType deliverytype;

    private Timestamp orderPlacedTime;

     private Customer customer;

     private Address shippingAddress;

     private Set<Item> items;

     private Set<Payment> payments;

     public OrderItemDTO(){

     }
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryType getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(DeliveryType deliverytype) {
        this.deliverytype = deliverytype;
    }

    public Timestamp getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(Timestamp orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }
}
