package com.egen.model;

import com.egen.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Entity
public class Orders {
    @Id
    private String id;
    private String customer_id;
    private Double order_total;
    private Double order_subtotal;
    private Double order_tax;
    private Timestamp creation_time;
    private Timestamp modification_time;
    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;


    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Item> items;

    @OneToOne(cascade = {CascadeType.ALL})
    private Shipping shipping;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Payment> payments;

    public Orders(){
        this.id = UUID.randomUUID().toString();
    }

    public Orders( String customer_id, Double order_total, Double order_subtotal, Double order_tax, Timestamp creation_time, Timestamp modification_time, OrderStatus order_status, List<Item> items, Shipping shipping, List<Payment> payments) {
        this.id = UUID.randomUUID().toString();
        this.customer_id = customer_id;
        this.order_total = order_total;
        this.order_subtotal = order_subtotal;
        this.order_tax = order_tax;
        this.creation_time = creation_time;
        this.modification_time = modification_time;
        this.order_status = OrderStatus.PLACED;
        this.items = items;
        this.shipping = shipping;
        this.payments = payments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(Double order_total) {
        this.order_total = order_total;
    }

    public Double getOrder_subtotal() {
        return order_subtotal;
    }

    public void setOrder_subtotal(Double order_subtotal) {
        this.order_subtotal = order_subtotal;
    }

    public Double getOrder_tax() {
        return order_tax;
    }

    public void setOrder_tax(Double order_tax) {
        this.order_tax = order_tax;
    }

    public OrderStatus getOrder_status() {
        return order_status;
    }

    public void setOrder_status(OrderStatus order_status) {
        this.order_status = order_status;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getModification_time() {
        return modification_time;
    }

    public void setModification_time(Timestamp modification_time) {
        this.modification_time = modification_time;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", order_total=" + order_total +
                ", order_subtotal=" + order_subtotal +
                ", order_tax=" + order_tax +
                ", creation_time=" + creation_time +
                ", modification_time=" + modification_time +
                ", order_status=" + order_status +
                ", items=" + items +
                ", shipping=" + shipping +
                ", payments=" + payments +
                '}';
    }
}
