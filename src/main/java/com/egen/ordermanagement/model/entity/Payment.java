package com.egen.ordermanagement.model.entity;

import com.egen.ordermanagement.model.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "order_payment_confirmation_number")
    private String confirmationNumber;

    @Column(name = "order_payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "order_payment_amount")
    private Double amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_id", referencedColumnName = "billing_id")
    private Billing billing;

    public Payment() {}
}
