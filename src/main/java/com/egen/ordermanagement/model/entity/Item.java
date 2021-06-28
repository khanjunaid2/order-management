package com.egen.ordermanagement.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ITEM")
@Accessors(chain = true)
@Data
public class Item implements Serializable {

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

    @ManyToOne(targetEntity=CustomerOrder.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private CustomerOrder orders;

    public Item() {}

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Item )) return false;
        return itemId != null && itemId.equals(((Item) o).getItemId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
