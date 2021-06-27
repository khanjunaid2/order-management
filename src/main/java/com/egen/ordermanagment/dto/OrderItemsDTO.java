package com.egen.ordermanagment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class OrderItemsDTO {

    private String orderItemId;
    private String orderItemName;
    private int orderItemQty;
    private Double orderItemUnitPrice;
}
