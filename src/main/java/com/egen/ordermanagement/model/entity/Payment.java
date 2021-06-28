package com.egen.ordermanagement.model.entity;

import com.egen.ordermanagement.model.enums.PaymentType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PAYMENT")
@Accessors(chain = true)
@Data
public class Payment implements Serializable {

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

    @ManyToOne(targetEntity=CustomerOrder.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private CustomerOrder orders;

    public Payment() {}
}