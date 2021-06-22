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
public class OrderItems {

    @Id
    private String itemId;

    private String itemName;

    private Integer itemQty;

    private double amount;


}
