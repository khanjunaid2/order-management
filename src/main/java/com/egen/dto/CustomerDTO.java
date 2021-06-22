package com.egen.dto;

import com.egen.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {


    private long id;


    private String firstName;


    private String lastName;


    private String email;


    private String phoneNumber;

    public Set<Order> orders = new HashSet<>();
}
