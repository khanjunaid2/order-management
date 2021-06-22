package com.egen.ordermanagement.model;

import com.egen.ordermanagement.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amount;

    private Timestamp paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMode;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address billingAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    private Orders orders;

    public Payment(double amount, Timestamp paymentDate, PaymentMethod paymentMode,
                   Address billingAddress, Orders orders) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMode = paymentMode;
        this.billingAddress = billingAddress;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public Payment setId(Long id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Payment setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public Payment setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public PaymentMethod getPaymentMode() {
        return paymentMode;
    }

    public Payment setPaymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Payment setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public Payment setOrders(Orders orders) {
        this.orders = orders;
        return this;
    }
}
