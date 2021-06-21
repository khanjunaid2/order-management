package com.egen.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    private String payment_id;
    private Double payment_confirmation_number;
    private Double order_payment_amount;
    private String order_payment_method;
    private Timestamp order_payment_date;

    @OneToOne(cascade = {CascadeType.ALL})
    private Billing billing;    //Foreign


    public Payment(){

        this.payment_id = UUID.randomUUID().toString();
    }

    public Payment(Double payment_confirmation_number, Double order_payment_amount, String order_payment_method, Timestamp order_payment_date, Billing billing) {
        this.payment_id = UUID.randomUUID().toString();
        this.payment_confirmation_number = payment_confirmation_number;
        this.order_payment_amount = order_payment_amount;
        this.order_payment_method = order_payment_method;
        this.order_payment_date = order_payment_date;
        this.billing = billing;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public Double getPayment_confirmation_number() {
        return payment_confirmation_number;
    }

    public void setPayment_confirmation_number(Double payment_confirmation_number) {
        this.payment_confirmation_number = payment_confirmation_number;
    }

    public Double getOrder_payment_amount() {
        return order_payment_amount;
    }

    public void setOrder_payment_amount(Double order_payment_amount) {
        this.order_payment_amount = order_payment_amount;
    }

    public String getOrder_payment_method() {
        return order_payment_method;
    }

    public void setOrder_payment_method(String order_payment_method) {
        this.order_payment_method = order_payment_method;
    }

    public Timestamp getOrder_payment_date() {
        return order_payment_date;
    }

    public void setOrder_payment_date(Timestamp order_payment_date) {
        this.order_payment_date = order_payment_date;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }
}
