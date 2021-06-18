package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.Orders;
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
public class ItemDto implements Serializable {

    long id;
    @JsonProperty(value = "itemName")
    private String itemName;
    @JsonProperty(value = "itemPrice")
    private double itemPrice;
    @JsonProperty(value = "quantityInStock")
    private int quantityInStock;
    @JsonProperty(value = "orders")
    private Orders orders;

}
