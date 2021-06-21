package com.egen.ordermanagment.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
//@NamedQueries({
//        @NamedQuery(name="Orders.getAllOrders",query = "SELECT ord FROM Orders ord"),
//        @NamedQuery(name="Orders.getOrderById", query="SELECT ord FROM Orders ord WHERE ord.order_id =:paramId"),
//        @NamedQuery(name="Order.getAllOrdersWithInInterval", query = "SELECT ord FROM Orders ord WHERE ord.order_created_date BETWEEN :paramstart AND :paramend"),
//        @NamedQuery(name ="Order.top10OrdersWithHighestDollarAmountInZip", query = "SELECT ord FROM Orders ord JOIN Address ON ord.shippingAddress = Address.address_id " +
//                "WHERE Address.order_zip =: paramzip ORDER BY ord.order_total")
//})
public class Orders {

    @Id
    private String order_id;
    private String customer_id;
    private Double order_sub_total;
    private Double order_total;
    private Double order_tax;

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

    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderItems> orderItemsList;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Payment> paymentDetail;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="shipping_address_id")
    private Address shippingAddress;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Customer customer;

    public Orders() {
        this.order_id = UUID.randomUUID().toString();
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Double getOrder_sub_total() {
        return order_sub_total;
    }

    public void setOrder_sub_total(Double order_sub_total) {
        this.order_sub_total = order_sub_total;
    }

    public Double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(Double order_total) {
        this.order_total = order_total;
    }

    public Double getOrder_tax() {
        return order_tax;
    }

    public void setOrder_tax(Double order_tax) {
        this.order_tax = order_tax;
    }

    public Timestamp getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Timestamp orderCreated) {
        this.orderCreated = orderCreated;
    }

    public Timestamp getOrderModified() {
        return orderModified;
    }

    public void setOrderModified(Timestamp orderModified) {
        this.orderModified = orderModified;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ShippingType getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShippingType shipmentType) {
        this.shipmentType = shipmentType;
    }

    public List<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(List<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public List<Payment> getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(List<Payment> paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "order_id='" + order_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", order_sub_total=" + order_sub_total +
                ", order_total=" + order_total +
                ", order_tax=" + order_tax +
                ", orderCreated=" + orderCreated +
                ", orderModified=" + orderModified +
                ", orderStatus=" + orderStatus +
                ", shipmentType=" + shipmentType +
                ", orderItemsList=" + orderItemsList +
                ", paymentDetail=" + paymentDetail +
                ", shippingAddress=" + shippingAddress +
                ", customer=" + customer +
                '}';
    }
}