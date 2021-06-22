package com.example.ordermanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    private String  orderItemId;

    private String itemName;
    private double price;

    private int quantity;

//    @OneToMany(mappedBy = "order_item", cascade = {CascadeType.ALL})
//    private List<OrderItem> orderItems;

    public Item() {
        this.orderItemId = UUID.randomUUID().toString();
    }


}
