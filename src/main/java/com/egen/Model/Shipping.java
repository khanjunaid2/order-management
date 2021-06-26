package com.egen.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Shipping")
@Data
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shippingId")
    private String shippingId;
    @Column(name = "shippingMode")
    private String shippingMode;
    @Column(name = "shippingCharge")
    private double shippingCharge;

    public Shipping() {}
    public Shipping(String shippingId, String shippingMode, double shippingCharge) {
        super();
        this.shippingId = shippingId;
        this.shippingMode = shippingMode;
        this.shippingCharge = shippingCharge;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingMode() {
        return shippingMode;
    }

    public void setShippingMethod(String shippingMode) {
        this.shippingMode = shippingMode;
    }

    public double getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(double shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    @Override
    public String toString() {
        return "shipping [shippingId=" + shippingId + ", shippingMethod=" + shippingMode + ", shippingCharge="
                + shippingCharge + "]";
    }
}