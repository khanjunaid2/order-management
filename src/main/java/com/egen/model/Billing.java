package com.egen.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Billing {
    @Id
    private String billing_id;
    private String billing_addressline1;
    private String billing_addressline2;
    private String billing_city;
    private String billing_state;
    private String billing_zip;

    public Billing(String billing_id){
        this.billing_id = billing_id;
    }

    public Billing() {
        this.billing_id = UUID.randomUUID().toString();
    }

    public Billing( String billing_addressline1, String billing_addressline2, String billing_city, String billing_state, String billing_zip) {
        this.billing_id = UUID.randomUUID().toString();
        this.billing_addressline1 = billing_addressline1;
        this.billing_addressline2 = billing_addressline2;
        this.billing_city = billing_city;
        this.billing_state = billing_state;
        this.billing_zip = billing_zip;
    }

    public void setBilling_id(String billing_id) {
        this.billing_id = billing_id;
    }

    public String getBilling_addressline1() {
        return billing_addressline1;
    }

    public void setBilling_addressline1(String billing_addressline1) {
        this.billing_addressline1 = billing_addressline1;
    }

    public String getBilling_addressline2() {
        return billing_addressline2;
    }

    public void setBilling_addressline2(String billing_addressline2) {
        this.billing_addressline2 = billing_addressline2;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public String getBilling_state() {
        return billing_state;
    }

    public void setBilling_state(String billing_state) {
        this.billing_state = billing_state;
    }

    public String getBilling_zip() {
        return billing_zip;
    }

    public void setBilling_zip(String billing_zip) {
        this.billing_zip = billing_zip;
    }


}
