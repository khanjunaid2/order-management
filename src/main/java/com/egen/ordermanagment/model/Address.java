package com.egen.ordermanagment.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Address {
    @Id
    private String address_id;

    private String order_addressline1;
    private String order_addressline2;
    private String order_city;
    private String order_state;
    private int order_zip;

    public Address() {
        this.address_id = UUID.randomUUID().toString();
    }

    public String getShipping_id() {
        return address_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getOrder_addressline1() {
        return order_addressline1;
    }

    public void setOrder_addressline1(String order_addressline1) {
        this.order_addressline1 = order_addressline1;
    }

    public String getOrder_addressline2() {
        return order_addressline2;
    }

    public void setOrder_addressline2(String order_addressline2) {
        this.order_addressline2 = order_addressline2;
    }

    public String getOrder_city() {
        return order_city;
    }

    public void setOrder_city(String order_city) {
        this.order_city = order_city;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public int getOrder_zip() {
        return order_zip;
    }

    public void setOrder_zip(int order_zip) {
        this.order_zip = order_zip;
    }

    public void setShipping_id(String shipping_id) {
        this.address_id = shipping_id;
    }

    public String getOrder_shipping_addressline1() {
        return order_addressline1;
    }

    public void setOrder_shipping_addressline1(String order_shipping_addressline1) {
        this.order_addressline1 = order_shipping_addressline1;
    }

    public String getOrder_shipping_addressline2() {
        return order_addressline2;
    }

    public void setOrder_shipping_addressline2(String order_shipping_addressline2) {
        this.order_addressline2 = order_shipping_addressline2;
    }

    public String getOrder_shipping_city() {
        return order_city;
    }

    public void setOrder_shipping_city(String order_shipping_city) {
        this.order_city = order_shipping_city;
    }

    public String getOrder_shipping_state() {
        return order_state;
    }

    public void setOrder_shipping_state(String order_shipping_state) {
        this.order_state = order_shipping_state;
    }

    public int getOrder_shipping_zip() {
        return order_zip;
    }

    public void setOrder_shipping_zip(int order_shipping_zip) {
        this.order_zip = order_shipping_zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_id='" + address_id + '\'' +
                ", order_addressline1='" + order_addressline1 + '\'' +
                ", order_addressline2='" + order_addressline2 + '\'' +
                ", order_city='" + order_city + '\'' +
                ", order_state='" + order_state + '\'' +
                ", order_zip=" + order_zip +
                '}';
    }
}
