package com.egen.dto;

import com.egen.model.enums.ShipmentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingDto {
    private String shippingId;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String zip;
    private ShipmentType shipmentType;
}
