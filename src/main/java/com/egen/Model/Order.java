package com.egen.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "Order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    private String orderId;
    @Column(name = "status")
    private OrderStatus status;
    @Column(name = "orderCustomerId")
    private String order_customer_id;
    @OneToMany(mappedBy = "order" , cascade = {CascadeType.ALL})
    private List<Product> products;
    @Column(name = "orderSubtotal")
    private double order_subtotal;
    @Column(name = "orderTax")
    private double order_tax;
    @Column(name = "orderTotal")
    private double order_total;
    @Column(name = "createdDate")
    private ZonedDateTime created_date;
    @Column(name = "modifiedDate")
    private ZonedDateTime modified_date;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Payment> payment;
    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    private Shipping shipping;
    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    private Address order_billing_address;
    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    private Address order_shipping_address;

    public Order(){}
    public Order(OrderStatus status, String order_customer_id, List<Product> products, List<Payment> payment, Shipping shipping,
                 ZonedDateTime created_date, ZonedDateTime modified_date, Address order_billing_address, Address order_shipping_address) {
        this.status = status;
        this.order_customer_id = order_customer_id;
        this.products = products;
        this.order_subtotal = this.products.stream().reduce(0.0, (sum, product) -> sum + product.getSubTotalWithoutTax(), Double::sum);
        this.order_tax = this.products.stream().reduce(0.0, (sum, product) -> sum + product.getSubtotalTax(), Double::sum);
        this.payment = payment;
        this.shipping = shipping;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.order_total = this.order_subtotal + this.order_tax + this.shipping.getShippingCharge();
        this.order_billing_address = order_billing_address;
        this.order_shipping_address = order_shipping_address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setId(String id) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return status;
    }

    public void setOrderStatus(OrderStatus status){
        this.status = status;
    }

    public String getOrderCustomerId() {
        return order_customer_id;
    }

    public void setOrderCustomerId(String order_customer_id) {
        this.order_customer_id = order_customer_id;
    }

    public List<Product> getOrderProducts() {
        return products;
    }

    public void setOrderProducts(List<Product> products) {
        this.products = products;
    }

    public double getOrderSubtotal() {
        return order_subtotal;
    }

    public void setOrderSubtotal(double order_subtotal) {
        this.order_subtotal = order_subtotal;
    }

    public double getOrderTax() {
        return order_tax;
    }

    public void setOrderTax(double order_tax) {
        this.order_tax = order_tax;
    }

    public ZonedDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(ZonedDateTime created_date) {
        this.created_date = created_date;
    }

    public ZonedDateTime getModified_date() {
        return modified_date;
    }

    public void setModified_date(ZonedDateTime modified_date) {
        this.modified_date = modified_date;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public double getOrder_total() {
        return order_total;
    }

    public void setOrder_total(double order_total) {
        this.order_total = order_total;
    }

    public Address getOrder_billing_address() {
        return order_billing_address;
    }

    public void setOrder_billing_address(Address order_billing_address) {
        this.order_billing_address = order_billing_address;
    }

    public Address getOrder_shipping_address() {
        return order_shipping_address;
    }

    public void setOrder_shipping_address(Address order_shipping_address) {
        this.order_shipping_address = order_shipping_address;
    }
}
