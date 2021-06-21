package com.egen.DTO;

import com.egen.Model.Address;

import java.time.ZonedDateTime;
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
public class PaymentDTO implements Serializable{

    @JsonProperty(value ="PaymentId")
    private String paymentId;
    @JsonProperty(value = "PaymentMode")
    private String paymentMode;
    @JsonProperty(value = "BillingAddress")
    private Address billingAddress;
    @JsonProperty(value = "PaymentDate")
    private ZonedDateTime paymentDate;
    @JsonProperty(value = "OrderNumber")
    private String orderNumber;
}
