package com.egen.Model;

import java.time.ZonedDateTime;

public class Payment {

    private String paymentId;
    private String paymentMode;
    private Address billingAddress;
    private ZonedDateTime paymentDate;
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