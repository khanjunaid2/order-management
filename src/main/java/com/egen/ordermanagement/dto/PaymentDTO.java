package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.entity.CustomerOrder;
import com.egen.ordermanagement.model.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO implements Serializable {

    private String paymentId;
    private String confirmationNumber;
    private PaymentType paymentType;
    private Double amount;
    private BillingDTO billing;
    private CustomerOrder orders;
}
