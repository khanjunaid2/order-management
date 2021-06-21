package com.egen.ordermanagment.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    private String payment_id;

    private Timestamp order_payment_date;
    private Double order_payment_confirmation_number;
    private Double order_payment_amount;

    @Column(name="order_payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address billingAddress;

    public Payment(){
        this.payment_id = UUID.randomUUID().toString();
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public Timestamp getOrder_payment_date() {
        return order_payment_date;
    }

    public void setOrder_payment_date(Timestamp order_payment_date) {
        this.order_payment_date = order_payment_date;
    }

    public Double getOrder_payment_confirmation_number() {
        return order_payment_confirmation_number;
    }

    public void setOrder_payment_confirmation_number(Double order_payment_confirmation_number) {
        this.order_payment_confirmation_number = order_payment_confirmation_number;
    }

    public Double getOrder_payment_amount() {
        return order_payment_amount;
    }

    public void setOrder_payment_amount(Double order_payment_amount) {
        this.order_payment_amount = order_payment_amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id='" + payment_id + '\'' +
                ", order_payment_date=" + order_payment_date +
                ", order_payment_confirmation_number=" + order_payment_confirmation_number +
                ", order_payment_amount=" + order_payment_amount +
                ", paymentMethod=" + paymentMethod +
                ", billingAddress=" + billingAddress +
                '}';
    }
}
