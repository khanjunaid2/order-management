package com.egen.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Payments {
    @Id
    private String id;
    private String amount;
    private String orderPaymentMethod;
    private String orderPaymentDate;
    private String orderPaymentConfirmationNumber;

    @OneToOne
    private Address billingAddress;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;


    public Payments() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public void setOrderPaymentMethod(String orderPaymentMethod) {
        this.orderPaymentMethod = orderPaymentMethod;
    }

    public String getOrderPaymentDate() {
        return orderPaymentDate;
    }

    public void setOrderPaymentDate(String orderPaymentDate) {
        this.orderPaymentDate = orderPaymentDate;
    }

    public String getOrderPaymentConfirmationNumber() {
        return orderPaymentConfirmationNumber;
    }

    public void setOrderPaymentConfirmationNumber(String orderPaymentConfirmationNumber) {
        this.orderPaymentConfirmationNumber = orderPaymentConfirmationNumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payments(String amount, String order_payment_method, String order_payment_date, String order_payment_confirmation_number, Address billingAddress, Order order) {
        this.amount = amount;
        this.orderPaymentMethod = order_payment_method;
        this.orderPaymentDate = order_payment_date;
        this.orderPaymentConfirmationNumber = order_payment_confirmation_number;
        this.billingAddress = billingAddress;
        this.order = order;
    }
}