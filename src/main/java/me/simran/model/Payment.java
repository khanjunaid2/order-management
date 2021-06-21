package me.simran.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Payment {
    @Id
    @Column(columnDefinition="VARCHAR(36)")
    private String paymentConfirmation;
    private float subTotal;
    private float tax;
    private float shippingCharges;
    private float total;
    private java.sql.Date paymentDate;

    /** One Payment Confirmation is payed with different methods {Split}*/
    @OneToMany
    private List<PaymentMethods> paymentMethods;

    public Payment(){
        this.paymentConfirmation = UUID.randomUUID().toString();
    }

    public String getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(String paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(float shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(java.sql.Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<PaymentMethods> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethods> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentConfirmation='" + paymentConfirmation + '\'' +
                ", subTotal=" + subTotal +
                ", tax=" + tax +
                ", shippingCharges=" + shippingCharges +
                ", total=" + total +
                ", paymentDate=" + paymentDate +
                ", paymentMethods=" + paymentMethods +
                '}';
    }
}
