package com.egen.dto;

import com.egen.model.Address;
import com.egen.model.Order;
import com.egen.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private long id;

    private PaymentMethod paymentMethod;

    private long cardNumber;

    private long paymentConfirmationNumber;

    public Address billingAddress;

    public Order orders;
}
