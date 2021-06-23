package com.egen.model.entity;

import com.egen.model.enums.ShipmentType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "SHIPPING")
@Getter
@Setter
@Entity
public class Shipping {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "shipping_id")
    private String shippingId;

    @Column(name = "shipping_addressline1")
    private String addressline1;

    @Column(name = "shipping_addressline2")
    private String addressline2;

    @Column(name = "shipping_city")
    private String city;

    @Column(name = "shipping_state")
    private String state;

    @Column(name = "shipping_zip")
    private String zip;

    @Column(name = "shipment_type")
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    public Shipping() {
    }
}
