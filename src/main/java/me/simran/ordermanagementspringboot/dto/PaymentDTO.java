package me.simran.ordermanagementspringboot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.simran.ordermanagementspringboot.enums.PaymentType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {
    private Long id;

    private double paidAmount;

    private PaymentType paymentMode;
}
