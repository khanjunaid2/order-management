package com.egen.dto;


import com.egen.enums.OrderStatus;
import com.egen.model.Address;
import com.egen.model.Customer;
import com.egen.model.Item;
import com.egen.model.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO implements Serializable {

    private String orderId;
    @JsonProperty
    private OrderStatus orderStatus;
    @JsonProperty
    private Customer orderCustomer;
    @JsonProperty
    private List<Item> orderItems;
    @JsonProperty
    private double orderSubtotal;
    @JsonProperty
    private double orderTax;
    @JsonProperty
    private double orderShippingCharges;
    @JsonProperty
    private double orderTotal;
    @JsonProperty
    private List<Payment> orderPayments;
    @JsonProperty
    private Address orderBillingAddress;
    @JsonProperty
    private Address orderShippingAddress;
    @JsonProperty
    private ZonedDateTime orderCreatedOn;
    @JsonProperty
    private ZonedDateTime orderModifiedOn;
    @JsonProperty
    private ZonedDateTime orderCompletedOn;

    public OrderDTO(String orderId, OrderStatus orderStatus, Customer orderCustomer, List<Item> orderItems, double orderSubtotal, double orderTax, double orderShippingCharges, double orderTotal, List<Payment> orderPayments, Address orderBillingAddress, Address orderShippingAddress, ZonedDateTime orderCreatedOn, ZonedDateTime orderModifiedOn, ZonedDateTime orderCompletedOn) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderCustomer = orderCustomer;
        this.orderItems = orderItems;
        this.orderSubtotal = orderSubtotal;
        this.orderTax = orderTax;
        this.orderShippingCharges = orderShippingCharges;
        this.orderTotal = orderTotal;
        this.orderPayments = orderPayments;
        this.orderBillingAddress = orderBillingAddress;
        this.orderShippingAddress = orderShippingAddress;
        this.orderCreatedOn = orderCreatedOn;
        this.orderModifiedOn = orderModifiedOn;
        this.orderCompletedOn = orderCompletedOn;
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

    public Customer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(Customer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(double orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public double getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(double orderTax) {
        this.orderTax = orderTax;
    }

    public double getOrderShippingCharges() {
        return orderShippingCharges;
    }

    public void setOrderShippingCharges(double orderShippingCharges) {
        this.orderShippingCharges = orderShippingCharges;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<Payment> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(List<Payment> orderPayments) {
        this.orderPayments = orderPayments;
    }

    public Address getOrderBillingAddress() {
        return orderBillingAddress;
    }

    public void setOrderBillingAddress(Address orderBillingAddress) {
        this.orderBillingAddress = orderBillingAddress;
    }

    public Address getOrderShippingAddress() {
        return orderShippingAddress;
    }

    public void setOrderShippingAddress(Address orderShippingAddress) {
        this.orderShippingAddress = orderShippingAddress;
    }

    public ZonedDateTime getOrderCreatedOn() {
        return orderCreatedOn;
    }

    public void setOrderCreatedOn(ZonedDateTime orderCreatedOn) {
        this.orderCreatedOn = orderCreatedOn;
    }

    public ZonedDateTime getOrderModifiedOn() {
        return orderModifiedOn;
    }

    public void setOrderModifiedOn(ZonedDateTime orderModifiedOn) {
        this.orderModifiedOn = orderModifiedOn;
    }

    public ZonedDateTime getOrderCompletedOn() {
        return orderCompletedOn;
    }

    public void setOrderCompletedOn(ZonedDateTime orderCompletedOn) {
        this.orderCompletedOn = orderCompletedOn;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderCustomer=" + orderCustomer +
                ", orderItems=" + orderItems +
                ", orderSubtotal=" + orderSubtotal +
                ", orderTax=" + orderTax +
                ", orderShippingCharges=" + orderShippingCharges +
                ", orderTotal=" + orderTotal +
                ", orderPayments=" + orderPayments +
                ", orderBillingAddress=" + orderBillingAddress +
                ", orderShippingAddress=" + orderShippingAddress +
                ", orderCreatedOn=" + orderCreatedOn +
                ", orderModifiedOn=" + orderModifiedOn +
                ", orderCompletedOn=" + orderCompletedOn +
                '}';
    }
}
