package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.enums.ShipmentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingDTO implements Serializable {

    private String shippingId;
    private ShipmentType shipmentType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
}
