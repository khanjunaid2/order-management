package com.example.ordermanagement.DTO;

import lombok.Data;

@Data
public class ItemDto {

//    @Id
//    private String  orderItemId;

    private String itemName;
    private double price;

    private int quantity;
}
