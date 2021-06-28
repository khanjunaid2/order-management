package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO implements Serializable {

    private String orderId;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private String customerId;
    private Double total;
    private Double subtotal;
    private Double tax;
    private OrderStatus status;
    private List<ItemDTO> items = new ArrayList<>();
    private List<PaymentDTO> payments = new ArrayList<>();
    private ShippingDTO shipping;
}
