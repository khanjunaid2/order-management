package com.example.ordermanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;


@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    @Id
    String addressId;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;

    public Address() {
        this.addressId = UUID.randomUUID().toString();
    }
}
