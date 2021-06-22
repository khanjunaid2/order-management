package me.simran.ordermanagementspringboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.simran.ordermanagementspringboot.enums.DeliveryType;
import me.simran.ordermanagementspringboot.enums.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private OrderStatus orderStatus;

    private String customerId;

    private double subTotal;

    private double tax;

    private double totalAmount;

    @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Address shippingAddress;

    @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Address billingAddress;

    private boolean isBillingAddressSame;

    private DeliveryType deliveryType;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
    private List<Item> items;

    @OneToOne(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Payment paymentDetails;

}
