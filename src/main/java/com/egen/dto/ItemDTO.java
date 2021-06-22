package com.egen.dto;

import com.egen.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private long id;

    private String name;

    private double quantity;

    private double cost;

    public Order orders;
}
