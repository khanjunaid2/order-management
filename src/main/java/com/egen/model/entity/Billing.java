package com.egen.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "BILLING")
@Getter
@Setter
@Entity
public class Billing {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "billing_id")
    private String billingId;

    @Column(name = "billing_addressline1")
    private String addressline1;

    @Column(name = "billing_addressline2")
    private String addressline2;

    @Column(name = "billing_city")
    private String city;

    @Column(name = "billing_state")
    private String state;

    @Column(name = "billing_zip")
    private String zip;

    public Billing() {
    }
}
