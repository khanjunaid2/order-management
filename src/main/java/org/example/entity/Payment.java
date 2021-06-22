package org.example.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.enums.PaymentMethod;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "Payment")
@Data
public class Payment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "payment_id", columnDefinition = "VARCHAR(36)")
    private String paymentId;

    @Column(name = "order_payment_amount")
    private double paidAmount;

    @Column(name = "order_payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "order_payment_date")
    private Timestamp paymentDate;

    @Column(name = "order_payment_confirmation_number")
    private String paymentConfirmationNumber;

    public Payment(){

    }

}

