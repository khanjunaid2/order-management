package com.egen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer", indexes = {
        @Index(columnList = "customer_id", name = "customer_id_index")
})
@NamedQueries({
        @NamedQuery(name="Customer.findAll",
                query = "SELECT cust FROM Customer cust ORDER BY cust.id ")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private long id;

    @Column(name = "customer_firstname")
    private String firstName;

    @Column(name = "customer_lastname")
    private String lastName;

    @Column(name ="customer_email" ,unique = true)
    private String email;

    @Column(name = "customer_number")
    private String phoneNumber;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonIgnore
    public List<Order> orders;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String phoneNumber, List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
