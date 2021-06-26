package com.egen.ordermanagement.dto;

import com.egen.ordermanagement.model.entity.Item;
import com.egen.ordermanagement.model.entity.Payment;
import com.egen.ordermanagement.model.entity.Shipping;
import com.egen.ordermanagement.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Accessors(chain = true)
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
    private List<ItemDTO> items = new ArrayList<>();
    private List<PaymentDTO> payments = new ArrayList<>();
    private ShippingDTO shipping;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", status=" + status +
                ", items=" + items +
                ", payments=" + payments +
                ", shipping=" + shipping +
                '}';
    }
}
