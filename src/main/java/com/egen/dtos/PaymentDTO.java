package com.egen.dtos;

import com.egen.enums.PaymentType;

public class PaymentDTO {

    private String paymentId;

    private double amountPaid;

    private PaymentType payType;

    private ordersDTO ordersdto;

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

    public PaymentType getPayType() {
        return payType;
    }

    public void setPayType(PaymentType payType) {
        this.payType = payType;
    }

    public ordersDTO getOrdersdto() {
        return ordersdto;
    }

    public void setOrdersdto(ordersDTO ordersdto) {
        this.ordersdto = ordersdto;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentId='" + paymentId + '\'' +
                ", amountPaid=" + amountPaid +
                ", payType=" + payType +
                ", ordersdto=" + ordersdto +
                '}';
    }
}
