package com.egen.ordermanagement.model.entity;

import com.egen.ordermanagement.model.enums.ShipmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "SHIPPING")
@Accessors(chain = true)
@Getter
@Setter
public class Shipping {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "shipping_id")
    private String shippingId;

    @Column(name = "order_shipping_type")
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @Column(name = "order_shipping_addressline1")
    private String addressLine1;

    @Column(name = "order_shipping_addressline2")
    private String addressLine2;

    @Column(name = "order_shipping_city")
    private String city;

    @Column(name = "order_shipping_state")
    private String state;

    @Column(name = "order_shipping_zip")
    private String zip;

    public Shipping() {}
}