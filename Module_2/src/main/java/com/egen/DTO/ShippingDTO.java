package com.egen.DTO;

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

public class ShippingDTO implements Serializable{

    @JsonProperty(value = "ShippingId")
    private String shippingId;
    @JsonProperty(value = "ShippingMode")
    private String shippingMode;
    @JsonProperty(value = "ShippingCharge")
    private double shippingCharge;
}
