package com.egen.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingDto {
    private String billingId;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String zip;
}
