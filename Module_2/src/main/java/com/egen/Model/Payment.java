package com.egen.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @Column(name = "PaymentId")
    private String paymentId;
    @Column(name = "PaymentMode")
    private String paymentMode;
    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    private Address billingAddress;
    @Column(name = "PaymentDate")
    private ZonedDateTime paymentDate;
    @Column(name = "orderNumber")
    private String orderNumber;

    public Payment() {}

    public Payment(String paymentId, String paymentMode, Address billingAddress, ZonedDateTime paymentDate,
                   String orderNumber) {
        super();
        this.paymentId = paymentId;
        this.paymentMode = paymentMode;
        this.billingAddress = billingAddress;
        this.paymentDate = paymentDate;
        this.orderNumber = orderNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public String getPaymentDate() {
        return paymentDate.toString();
    }
    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Address getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", paymentDate=" + paymentDate
                + ", orderNumber=" + orderNumber + ", billingAddress=" + billingAddress + "]";
    }
}