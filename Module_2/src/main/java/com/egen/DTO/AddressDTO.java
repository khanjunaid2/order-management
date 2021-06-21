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

public class AddressDTO implements Serializable{

    @JsonProperty(value = "AddressId")
    private String addressId;
    @JsonProperty(value = "Address_Line1")
    private String address_Line1;
    @JsonProperty(value = "Address_Line2")
    private String address_Line2;
    @JsonProperty(value = "City")
    private String city;
    @JsonProperty(value = "State")
    private String state;
    @JsonProperty(value = "ZipCode")
    private long zipCode;
}
