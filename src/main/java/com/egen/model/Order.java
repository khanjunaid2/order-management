package com.egen.model;

import com.egen.enums.OrderStatus;
import com.egen.enums.ShipmentMethods;
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
@Table(name = "orders")
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

    @Column(name = "is_billing_address_same")
    private boolean isBillingAddressSame = false;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name="delivery_type")
    private ShipmentMethods deliveryType;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Item> items;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Payment> paymentDetails;

    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    private Customer customer;

    public Order() {
    }

    public Order(String id, OrderStatus orderStatus, double subtotal, double totalAmount, double tax, Address shippingAddress, boolean isBillingAddressSame, ShipmentMethods deliveryType, Set<Item> items, Set<Payment> paymentDetails, Customer customer, Timestamp createdDate) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.subtotal = subtotal;
        this.totalAmount = totalAmount;
        this.tax = tax;
        this.shippingAddress = shippingAddress;
        this.isBillingAddressSame = isBillingAddressSame;
        this.deliveryType = deliveryType;
        this.items = items;
        this.paymentDetails = paymentDetails;
        this.customer = customer;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderStatus=" + orderStatus +
                ", subtotal=" + subtotal +
                ", totalAmount=" + totalAmount +
                ", tax=" + tax +
                ", shippingAddress=" + shippingAddress +
                ", isBillingAddressSame=" + isBillingAddressSame +
                ", deliveryType=" + deliveryType +
                ", items=" + items +
                ", paymentDetails=" + paymentDetails +
                ", customer=" + customer +
                ", createdDate=" + createdDate +
                '}';
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

    public ShipmentMethods getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(ShipmentMethods deliveryType) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}