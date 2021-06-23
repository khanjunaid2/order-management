package com.egen.passport.demo.entity;

import com.egen.passport.demo.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerOrder implements Serializable {

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

    @Column(name = "tax")
    private double tax;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL} , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Address> addresses;

   /* @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL})
    private Address billingAddress;*/

    @Column(name = "is_billing_address_same")
    private boolean isBillingAddressSame ;

    @Column(name = "delivery_type")
    private DeliveryType deliveryType;

    @OneToMany(mappedBy = "order" , cascade = {CascadeType.ALL} , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Item> items;

    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference
    private Payment paymentDetails;

    public CustomerOrder() {
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
        if(this.paymentDetails == null){
            this.paymentDetails = new Payment();
        }
        return this.paymentDetails;
    }

    public void setPaymentDetails(Payment paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
