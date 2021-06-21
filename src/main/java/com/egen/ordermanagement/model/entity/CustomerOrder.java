package com.egen.ordermanagement.model.entity;

import com.egen.ordermanagement.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_ORDER")
@Getter
@Setter
public class CustomerOrder {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_creation_date")
    private Timestamp creationDate;

    @Column(name = "order_modification_date")
    private Timestamp modificationDate;

    @Column(name = "order_customer_id")
    private String customerId;

    @Column(name = "order_total")
    private Double total;

    @Column(name = "order_subtotal")
    private Double subtotal;

    @Column(name = "order_tax")
    private Double tax;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id", referencedColumnName = "shipping_id")
    private Shipping shipping;

    public CustomerOrder() {}
}