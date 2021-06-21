package com.example.ordermanagement.models;

import com.example.ordermanagement.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    @Id
    private String paymentId;

    private double amount;
    private Timestamp order_payment_date;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;


    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
    }

}
