package com.egen.ordermanagement.model;

import com.egen.ordermanagement.enums.PaymentType;
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
    public Order order;

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

    public Order getOrder() {
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

    public void setOrder(Order order) {
        this.order = order;
    }
}
