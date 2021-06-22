package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.entity.Billing;
import com.egen.ordermanagement.model.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO {

    private String paymentId;
    private String confirmationNumber;
    private PaymentType paymentType;
    private Double amount;
    private BillingDTO billing;
}
