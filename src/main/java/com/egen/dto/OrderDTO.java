package com.egen.dto;

import com.egen.model.entity.Item;
import com.egen.model.entity.Payment;
import com.egen.model.entity.Shipping;
import com.egen.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
    private String orderId;
    private Timestamp creationTime;
    private Timestamp modificationTime;
    private String customerId;
    private Double total;
    private Double subtotal;
    private Double tax;
    private OrderStatus orderStatus;
    private List<Item> items = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private Shipping shipping;
}
