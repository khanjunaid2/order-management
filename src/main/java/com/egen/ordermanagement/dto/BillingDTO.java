package com.egen.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingDTO implements Serializable {

    private String billingId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zip;
}
