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

    @ManyToOne()
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

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentMethod getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMethod paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
