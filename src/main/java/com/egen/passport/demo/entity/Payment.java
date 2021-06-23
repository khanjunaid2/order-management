package com.egen.passport.demo.entity;

import com.egen.passport.demo.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "paid_amount")
    private double paidAmount;

    @Column(name = "payment_mode")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentMode;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(columnDefinition = "order_id")
   @JsonBackReference
    public CustomerOrder order;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public PaymentType getPaymentMode() {
        return paymentMode;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setPaymentMode(PaymentType paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }
}
