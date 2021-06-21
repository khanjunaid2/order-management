package me.simran.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
public class ShippingAddress {
    @Id
    @Column(columnDefinition="VARCHAR(36)")
    private String shippingId;

    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingState;
    private String shippingZip;

    public ShippingAddress(){
        this.shippingId = UUID.randomUUID().toString();
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingZip() {
        return shippingZip;
    }

    public void setShippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "shippingId='" + shippingId + '\'' +
                ", shippingAddress1='" + shippingAddress1 + '\'' +
                ", shippingAddress2='" + shippingAddress2 + '\'' +
                ", shippingCity='" + shippingCity + '\'' +
                ", shippingState='" + shippingState + '\'' +
                ", shippingZip='" + shippingZip + '\'' +
                '}';
    }
}

