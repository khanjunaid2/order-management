package com.egen.ordermanagement.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ITEMS")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "order_item_name")
    private String itemName;

    @Column(name = "order_item_qty")
    private int itemQuantity;

    @Column(name = "order_item_unit_price")
    private Double itemUnitPrice;

    public Item() {}
}
