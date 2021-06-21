package com.egen.passport.demo.dto;

import com.egen.passport.demo.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO {

    private Long id;
    @JsonProperty(value = "paidAmount")
    private double paidAmount;
    @JsonProperty(value = "paymentMode")
    private PaymentType paymentMode;
}
