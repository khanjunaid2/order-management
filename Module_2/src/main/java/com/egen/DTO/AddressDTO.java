package com.egen.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddressDTO implements Serializable{

    private long Id;
    @JsonProperty(value = "AddressId")
    private String addressId;
    @JsonProperty(value = "Address_Line1")
    private String address_Line1;
    @JsonProperty(value = "Address_Line2")
    private String address_Line2;
    @JsonProperty(value = "City")
    private String city;
    @JsonProperty(value = "State")
    private String state;
    @JsonProperty(value = "ZipCode")
    private long zipCode;

    public Long getId() {
        return Id;
    }
    public void setId(long Id){
        this.Id = Id;
    }
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
}
