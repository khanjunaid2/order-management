package com.egen.mapper;

import com.egen.dto.BillingDto;
import com.egen.model.entity.Billing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillingMapper {
    BillingDto toBillingDto(Billing billing);
    Billing toBilling(BillingDto billingDto);
}
