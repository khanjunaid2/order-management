package com.egen.model;

import com.egen.model.enums.PaymentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderPayment {

    @Id
    private String id;

    private double paidAmount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address billingAddress;


}
