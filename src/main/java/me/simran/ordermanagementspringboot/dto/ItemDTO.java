package me.simran.ordermanagementspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long id;

    private String pluId;

    public int quantity;

    public double amount;
}
