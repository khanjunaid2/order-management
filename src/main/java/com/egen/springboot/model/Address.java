package com.egen.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Address {

    @Id
    private String id;

    private String state;

    private String city;

    private String zip;

    private String addressLine1;

    private String addressLine2;

}
