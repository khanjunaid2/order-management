package com.egen.ordermanagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "BILLING")
@Getter
@Setter
public class Billing {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "billing_id")
    private String billingId;

    @Column(name = "order_billing_addressline1")
    private String addressLine1;

    @Column(name = "order_billing_addressline2")
    private String addressLine2;

    @Column(name = "order_billing_city")
    private String city;

    @Column(name = "order_billing_state")
    private String state;

    @Column(name = "order_billing_zip")
    private int zip;

    public Billing() {}
}
