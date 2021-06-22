package com.egen.ordermanagement.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @Column(columnDefinition="VARCHAR(36)")
    private String confirmationNumber;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Date payment_date;

    private String orderTax;
    private BigDecimal shippingCharges;
    private BigDecimal orderTotal;

    public Payment(){
        this.confirmationNumber = UUID.randomUUID().toString();
    }

    public String getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(String orderTax) {
        this.orderTax = orderTax;
    }

    public BigDecimal getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(BigDecimal shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", payment_date=" + payment_date +
                ", orderTax='" + orderTax + '\'' +
                ", shippingCharges=" + shippingCharges +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
