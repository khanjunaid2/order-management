package org.example.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.enums.OrderStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Table(name = "CustomerOrders")
//@NamedQueries({
//        @NamedQuery(name="Order.findAll",query = "SELECT order FROM Order order"),
//        @NamedQuery(name="Order.findWithinInterVal",query = "SELECT order FROM Order order WHERE order.orderCreated>=:paramStartTime AND order.orderCreated<=:paramEndTime"),
//        @NamedQuery(name="Order.findTop10OrdersWithHighestDollarAmountInZip",query = "SELECT order FROM Order order WHERE order.billingAddress.zip>=:paramZip ORDER BY order.orderTotal DESC")
//})
@Data
public class CustomerOrder {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "order_id", columnDefinition = "VARCHAR(36)")
    private String orderId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "order_total")
    private double orderTotal;

    @Column(name = "order_sub_total")
    private double orderSubTotal;

    @Column(name = "shipping_charges")
    private double shippingCharges;

    @Column(name = "order_tax")
    private double orderTax;

    @OneToOne(cascade = CascadeType.ALL)
    private Billing billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Shipping shippingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> paymentList;

    @Column(name = "order_created")
    private Timestamp orderCreated;

    @Column(name = "order_updated")
    private Timestamp orderUpdated;

}
