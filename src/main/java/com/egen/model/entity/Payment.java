package com.egen.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Table(name = "PAYMENTS")
@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_confirmation_number")
    private String confirmationNumber;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "payment_type")
    private String paymentType;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "billing_id")
    private Billing billing;

    public Payment(){
    }
}
