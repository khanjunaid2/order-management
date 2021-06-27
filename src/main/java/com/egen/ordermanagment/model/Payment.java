package com.egen.ordermanagment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
@Getter
@Setter
@Accessors(chain = true)
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name="order_payment_date")
    private Timestamp paymentDate;

    @Column(name="order_payment_confirmation_number")
    private Double paymentConfirmationNumber;

    @Column(name="order_payment_amount")
    private Double paymentAmount;

    @Column(name="order_payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address billingAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "orders_id")
    public Orders orders;

    public Payment(){}
}
