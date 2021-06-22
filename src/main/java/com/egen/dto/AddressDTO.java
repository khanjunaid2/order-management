package com.egen.dto;

import com.egen.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private long id;


    private AddressType type;


    private String addressLine1;


    private String addressLine2;


    private String city;


    private String state;


    private int zip;

    private Timestamp createdDate;


    private Timestamp modifiedDate;
}
