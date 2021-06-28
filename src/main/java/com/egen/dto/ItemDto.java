package com.egen.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {
    private String itemId;
    private String itemName;
    private int itemQuantity;
    private Double itemUnitPrice;
}
