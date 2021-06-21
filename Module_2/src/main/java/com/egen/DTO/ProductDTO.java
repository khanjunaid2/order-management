package com.egen.DTO;

import com.egen.Model.Order;
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
public class ProductDTO implements Serializable {

    @JsonProperty(value = "ProductId")
    private String id;
    @JsonProperty(value = "productName")
    private String productName;
    @JsonProperty(value = "productPrice")
    private double productPrice;
    @JsonProperty(value = "quantityAvailable")
    private int quantityAvailable;
    @JsonProperty(value = "subTotalWithoutTax")
    private double subTotalWithoutTax;
    @JsonProperty(value = "subTotalTax")
    private double subTotalTax;
    @JsonProperty(value = "order")
    private Order orders;
}