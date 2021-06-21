package com.example.ordermanagement.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
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

    public String getId() {
        return customerId;
    }

    public void setId(String id) {
        this.customerId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
