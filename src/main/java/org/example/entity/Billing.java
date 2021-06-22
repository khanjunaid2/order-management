package org.example.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "Billing")
@Data
public class Billing {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "billing_id", columnDefinition = "VARCHAR(36)")
    private String billingId;

    @Column(name = "billing_addressline1")
    private String addressLine1;

    @Column(name = "billing_addressline2")
    private String addressLine2;

    @Column(name = "billing_city")
    private String city;

    @Column(name = "billing_state")
    private String state;

    @Column(name = "billing_zip")
    private String zip;
}

