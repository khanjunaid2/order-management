package org.example.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "Order_Item")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_id", columnDefinition = "VARCHAR(36)")
    private String itemId;

    @Column(name = "order_item_name")
    private String itemName;

    @Column(name = "order_item_qty")
    private int itemQuantity;

    @Column(name = "order_item_price")
    private double itemPrice;

    public OrderItem(){

    }
}

