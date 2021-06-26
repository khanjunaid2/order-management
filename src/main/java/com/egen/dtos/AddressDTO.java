package com.egen.dtos;

import com.egen.enums.AddressType;

public class AddressDTO {

    private String addressId;

    private String AddressLine1;

    private String AddressLine2;

    private String city;

    private String state;

    private Double zipcode;

    private AddressType addressType;

    private ordersDTO ordersdto;

    public AddressDTO(){

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

    public ordersDTO getOrdersdto() {
        return ordersdto;
    }

    public void setOrdersdto(ordersDTO ordersdto) {
        this.ordersdto = ordersdto;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "addressId='" + addressId + '\'' +
                ", AddressLine1='" + AddressLine1 + '\'' +
                ", AddressLine2='" + AddressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", addressType=" + addressType +
                ", ordersdto=" + ordersdto +
                '}';
    }
}
