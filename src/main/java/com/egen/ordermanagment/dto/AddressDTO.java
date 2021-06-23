package com.egen.ordermanagment.dto;

import com.egen.ordermanagment.model.Orders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {

    private String addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zip;
//    public OrdersDTO orders;

}
