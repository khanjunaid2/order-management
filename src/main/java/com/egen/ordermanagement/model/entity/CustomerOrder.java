package com.egen.ordermanagement.model.entity;

import com.egen.ordermanagement.model.enums.OrderStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Accessors(chain = true)
@Data
public class CustomerOrder implements Serializable {

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

    @OneToMany(mappedBy = "orders", targetEntity=Item.class,
               cascade = CascadeType.ALL , fetch = FetchType.LAZY) //orphanRemoval = true
    private List<Item> items;

    @OneToMany(mappedBy = "orders", targetEntity=Payment.class,
               cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id", referencedColumnName = "shipping_id")
    private Shipping shipping;

    public CustomerOrder() {}

    // synchronize both order table and child table whenever a child element(Item ot Payment) is added or removed.
//    public void addItem(Item item){
//        items.add(item);
//        item.setOrders(this);
//    }
//
//    public void removeItem(Item item){
//        items.remove(item);
//        item.setOrders(null);
//    }
//
//    public void addPayment(Payment payment){
//        payments.add(payment);
//        payment.setOrders(this);
//    }
//
//    public void removePayment(Payment payment){
//        payments.remove(payment);
//        payment.setOrders(null);
//    }
}