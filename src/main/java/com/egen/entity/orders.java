package com.egen.entity;


import com.egen.enums.DeliveryType;
import com.egen.enums.OrderStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class orders {
    @Id
    String id;

    Double total;

    String customerId;

    @OneToOne(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private Payment paymentDetails;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Items> items;


    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    private List<Address> addresses;



    @Enumerated(EnumType.STRING)
    private DeliveryType type;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public orders(){
        this.id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Payment getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Payment paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public DeliveryType getType() {
        return type;
    }

    public void setType(DeliveryType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "orders{" +
                "id='" + id + '\'' +
                ", total=" + total +
                ", customerId='" + customerId + '\'' +
                ", paymentDetails=" + paymentDetails +
                ", items=" + items +
                ", addresses=" + addresses +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
