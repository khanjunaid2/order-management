package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.entity.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;
import java.util.List;

public class OrderDTO {
    private String id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String customerId;
    private List<ItemDTO> items;
    private PaymentDTO payment;
    private BillingDTO billingAddress;
    private DeliveryType deliveryType;
    private ShippingDTO shippingAddress;
    private Date dateCreated;
    private Date dateModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }

    public BillingDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public ShippingDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
