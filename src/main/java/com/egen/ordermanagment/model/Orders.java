package com.egen.ordermanagment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Accessors(chain = true)
public class Orders {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    private String Id;

    @Column(name="customer_id")
    private String customerId;

    @Column(name="order_sub_total")
    private Double orderSubTotal;

    @Column(name="order_total")
    private Double orderTotal;

    @Column(name="order_tax")
    private Double orderTax;

    @Column(name="created_at")
    private Timestamp orderCreated;

    @Column(name="modified_at")
    private Timestamp orderModified;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "shipment_type")
    @Enumerated(EnumType.STRING)
    private ShippingType shipmentType;

    @OneToMany(mappedBy = "orders", cascade = {CascadeType.ALL})
    private List<OrderItems> orderItemsList;

    @OneToOne(mappedBy = "orders", cascade = {CascadeType.ALL})
    private Payment paymentDetail;

    @OneToOne(mappedBy = "orders", cascade = {CascadeType.ALL})
    @JoinColumn(name="shipping_address_id")
    private Address shippingAddress;

    public Orders() {}

}