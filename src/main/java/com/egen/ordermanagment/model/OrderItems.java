package com.egen.ordermanagment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@Getter
@Setter
@Accessors(chain = true)
public class OrderItems {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="order_item_id")
    private String orderItemId;

    @Column(name="order_item_name")
    private String orderItemName;

    @Column(name="order_item_qty")
    private int orderItemQty;

    @Column(name="order_item_unit_price")
    private Double orderItemUnitPrice;

    @ManyToOne
    @JoinColumn(columnDefinition = "orders_id")
    public Orders orders;

   public OrderItems(){
   }


}
