package com.egen.Model;

public class Shipping {

    private String shippingId;
    private String shippingMode;
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