package com.example.ordermanagement.DTO;

import lombok.Data;

@Data
public class AddressDto {

//    @Id
//    private String id;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
}
