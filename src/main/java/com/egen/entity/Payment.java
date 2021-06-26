package com.egen.entity;


import com.egen.enums.PaymentType;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    private String paymentId;

    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private PaymentType payTpe;

    @OneToOne(fetch = FetchType.LAZY)
    private orders ords;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentType getPayTpe() {
        return payTpe;
    }

    public void setPayTpe(PaymentType payTpe) {
        this.payTpe = payTpe;
    }

    public orders getOrds() {
        return ords;
    }

    public void setOrds(orders ords) {
        this.ords = ords;
    }
}
