package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.entity.CustomerOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDTO implements Serializable {

    private String itemId;
    private String itemName;
    private int itemQuantity;
    private Double itemUnitPrice;
    private CustomerOrder orders;
}
