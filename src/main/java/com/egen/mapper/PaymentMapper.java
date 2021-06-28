package com.egen.mapper;

import com.egen.dto.OrderDto;
import com.egen.dto.PaymentDto;
import com.egen.model.entity.Orders;
import com.egen.model.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BillingMapper.class})
public interface PaymentMapper {
    PaymentDto toPaymentDto(Payment payment);
    Payment toPayment(PaymentDto paymentDto);
}
