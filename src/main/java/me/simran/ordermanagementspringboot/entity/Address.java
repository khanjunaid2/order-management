package me.simran.ordermanagementspringboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.simran.ordermanagementspringboot.enums.AddressType;

import javax.persistence.*;

@Entity
@Data
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;

    private String state;

    private Integer zip;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    private String addressLine1;

    private String addressLine2;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    public Order order;
}
