package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.entity.Item;
import com.egen.ordermanagement.model.entity.Payment;
import com.egen.ordermanagement.model.entity.Shipping;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

    private String orderId;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private String customerId;
    private Double total;
    private Double subtotal;
    private Double tax;
    private OrderStatus status;
    private List<Item> items = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private Shipping shipping;
}
