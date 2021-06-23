package com.egen.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table(name = "ITEMS")
@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_qty")
    private int itemQuantity;

    @Column(name = "item_unit_price")
    private Double itemUnitPrice;

    public Item(){
    }

}
