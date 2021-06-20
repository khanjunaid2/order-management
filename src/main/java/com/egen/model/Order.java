
package com.egen.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "sub_total")
    private double subtotal;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "tax")
    private double tax;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address shippingAddress;

    @Column(name = "is_billing_address_same")
    private boolean isBillingAddressSame = false;

    @Enumerated(EnumType.STRING)
    @Column(name="delivery_type")
    private DeliveryType deliveryType;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Item> items;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Payment> paymentDetails;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    private Customer customer;

    private Timestamp createdAt;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public boolean isBillingAddressSame() {
        return isBillingAddressSame;
    }

    public void setBillingAddressSame(boolean billingAddressSame) {
        isBillingAddressSame = billingAddressSame;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Payment> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<Payment> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}