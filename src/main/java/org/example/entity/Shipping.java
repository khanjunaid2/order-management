package org.example.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ShipmentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "Shipping")
@Getter
@Setter
@Data
public class Shipping {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "shipping_id", columnDefinition = "VARCHAR(36)")
    private String shippingId;

    @Column(name = "shipping_type")
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @Column(name = "shipping_addressline1")
    private String addressLine1;

    @Column(name = "shipping_addressline2")
    private String addressLine2;

    @Column(name = "shipping_city")
    private String city;

    @Column(name = "shipping_state")
    private String state;

    @Column(name = "shipping_zip")
    private String zip;

    public Shipping(){

    }


}

