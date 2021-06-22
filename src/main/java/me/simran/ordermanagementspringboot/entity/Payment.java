package me.simran.ordermanagementspringboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.simran.ordermanagementspringboot.enums.PaymentType;

import javax.persistence.*;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double paidAmount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentMode;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    public Order order;
}
