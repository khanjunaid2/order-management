package com.egen.ordermanagment.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "address_id")
    private String addressId;

    @Column(name = "order_billing_addressline1")
    private String addressLine1;

    @Column(name = "order_billing_addressline2")
    private String addressLine2;

    @Column(name = "order_billing_city")
    private String city;

    @Column(name = "order_billing_state")
    private String state;

    @Column(name = "order_billing_zip")
    private int zip;

    @OneToOne
    @JoinColumn(columnDefinition = "orders_id")
    public Orders orders;

    public Address(){

    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
