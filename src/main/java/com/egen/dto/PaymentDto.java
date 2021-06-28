package com.egen.dto;

import com.egen.model.entity.Billing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto {
    private String paymentId;
    private String confirmationNumber;
    private Double paymentAmount;
    private String paymentType;
    private BillingDto billing;
}
