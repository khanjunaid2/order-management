package com.egen.DTO;

import com.egen.Model.Address;

import java.time.ZonedDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO implements Serializable{

    @Id
    private long Id;
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

    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    public String getPaymentMode() {
        return paymentMode;
    }
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public String getPaymentDate() {
        return paymentDate.toString();
    }
    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Address getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
}
