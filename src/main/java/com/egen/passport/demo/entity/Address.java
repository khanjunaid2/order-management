package com.egen.passport.demo.entity;

import com.egen.passport.demo.enums.AddressType;
import com.egen.passport.demo.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@Builder
@AllArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "addrs_typ")
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @Column(name = "addressLine1")
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "order_id")
    @JsonBackReference
    public CustomerOrder order;

    public Address() {

    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getZip() {
        return zip;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }
}
