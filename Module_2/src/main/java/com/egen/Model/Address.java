package com.egen.Model;

public class Address {

    private String addressId;
    private String address_Line1;
    private String address_Line2;
    private String city;
    private String state;
    private long zipCode;

    public String getAddressId() {
        return addressId;
    }
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    public String getAddressLine1() {
        return address_Line1;
    }
    public void setAddressLine1(String address_Line1) {
        this.address_Line1 = address_Line1;
    }
    public String getAddressLine2() {
        return address_Line2;
    }
    public void setAddressLine2(String address_Line2) {
        this.address_Line2 = address_Line2;
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
    public long getZipCode() {
        return zipCode;
    }
    public void setZip(long zipCode) {
        this.zipCode = zipCode;
    }

    public Address() {super();}

    public Address(String addressId, String address_Line1, String address_Line2, String city, String state, long zipCode) {
        super();
        this.addressId = addressId;
        this.address_Line1 = address_Line1;
        this.address_Line2 = address_Line2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}