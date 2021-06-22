package com.egen.ordermanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data


public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;
}
