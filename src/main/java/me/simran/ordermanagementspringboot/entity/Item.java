package me.simran.ordermanagementspringboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pluId;

    public int quantity;

    public double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    public Order order;
}
