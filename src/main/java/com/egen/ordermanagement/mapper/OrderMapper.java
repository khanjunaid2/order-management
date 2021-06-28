package com.egen.ordermanagement.mapper;

import com.egen.ordermanagement.dto.*;
import com.egen.ordermanagement.model.entity.*;
import org.mapstruct.*;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;


@Mapper(mappingControl = DeepClone.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface OrderMapper {

    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class );

         @Mapping(target = "items", source = "items")
         @Mapping(target = "payments", source = "payments")
         CustomerOrder orderDTOToOrder(OrderDTO orderDTO, @Context CircularMappingResolver circularMappingResolver);

        @InheritInverseConfiguration
         OrderDTO orderToOrderDTO(CustomerOrder order, @Context CircularMappingResolver circularMappingResolver);

         @Mapping(target = "orders", source = "orders")
         Item itemDTOToItem(ItemDTO itemDTO, @Context CircularMappingResolver circularMappingResolver);

         @InheritInverseConfiguration
         ItemDTO itemToItemDTO(Item item, @Context CircularMappingResolver circularMappingResolver);

         @Mapping(target = "orders", source = "orders")
         PaymentDTO paymentToPaymentDTO(Payment payment);
         @InheritInverseConfiguration
         Payment paymentDTOTOPayment(PaymentDTO paymentDTO);

         ShippingDTO shippingToShippingDTO(Shipping shipping);
         Shipping shippingDTOToShipping(ShippingDTO shippingDTO);
         BillingDTO billingToBillingDTO(Billing billing);
         Billing BillingDTOToBilling(BillingDTO billingDTO);
}
