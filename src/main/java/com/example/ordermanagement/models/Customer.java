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
public class Customer {
    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

//    @OneToMany(mappedBy = "orders", cascade = {CascadeType.ALL})
//    private List<Orders> orders;
//
//    @OneToMany(mappedBy = "address", cascade = {CascadeType.ALL})
//    private List<Address> addresses;

    public Customer() {
        this.customerId = UUID.randomUUID().toString();
    }
}
