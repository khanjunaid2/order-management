package com.egen.ordermanagement.model;

import javax.persistence.*;

import com.egen.ordermanagement.enums.DeliveryType;
import com.egen.ordermanagement.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "sub_total")
    private double subTotal;

    @Column(name = "order_creation_date")
    private Timestamp creationDate;

    @Column(name = "order_modification_date")
    private Timestamp modificationDate;

    @Column(name = "tax")
    private double tax;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL})
    private Address shippingAddress;

    @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL})
    private Address billingAddress;

    @Column(name = "is_billing_address_same")
    private boolean isBillingAddressSame ;

    @Column(name = "delivery_type")
    private DeliveryType deliveryType;

    @OneToMany(mappedBy = "order" , cascade = {CascadeType.ALL})
    private List<Item> items;

    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    private Payment paymentDetails;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Address getShippingAddress() {
        if(shippingAddress == null){
            this.shippingAddress = new Address();
        }
        return  this.shippingAddress;
    }

    public boolean isBillingAddressSame() {
        return isBillingAddressSame;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public List<Item> getItems() {
        return items == null ? new ArrayList<Item>() : items;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setBillingAddressSame(boolean billingAddressSame) {
        isBillingAddressSame = billingAddressSame;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Payment getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Payment paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
