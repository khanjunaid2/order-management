package com.egen.mapper;

import com.egen.dto.ShippingDto;
import com.egen.model.entity.Shipping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShippingMapper {
    ShippingDto toShippingDto(ShippingMapper shipping);
    Shipping toShipping(ShippingDto shippingDto);
}
