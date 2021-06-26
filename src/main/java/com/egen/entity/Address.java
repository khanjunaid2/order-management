package com.egen.entity;

import com.egen.enums.AddressType;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "address")
public class Address{

    @Id
    private String addressId;

    private String AddressLine1;

    private String AddressLine2;

    private String city;

    private String state;

    private Double zipcode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private orders customerOrders;

    public Address()
    {
        this.addressId= UUID.randomUUID().toString();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
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

    public Double getZipcode() {
        return zipcode;
    }

    public void setZipcode(Double zipcode) {
        this.zipcode = zipcode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public orders getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(orders customerOrders) {
        this.customerOrders = customerOrders;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", AddressLine1='" + AddressLine1 + '\'' +
                ", AddressLine2='" + AddressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", addressType=" + addressType +
                ", customerOrders=" + customerOrders +
                '}';
    }
}

