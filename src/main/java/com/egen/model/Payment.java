package com.egen.model;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    private String id;


    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private float amountPaid;
    @OneToOne(cascade = {CascadeType.ALL})
    private Address billingAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
