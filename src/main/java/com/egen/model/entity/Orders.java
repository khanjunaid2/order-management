package com.egen.model.entity;

import com.egen.model.enums.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Table(name = "ORDERS")
@Entity
public class Orders {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "order_total")
    private Double total;

    @Column(name = "order_subtotal")
    private Double subtotal;

    @Column(name = "order_tax")
    private Double tax;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "modification_time")
    private Timestamp modificationTime;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = {CascadeType.ALL})
    private Shipping shipping;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Item> items;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Payment> payments;

}
