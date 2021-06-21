package com.egen.passport.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDTO {

    private Long id;
    @JsonProperty(value = "skuNum")
    private String skuNum;
    @JsonProperty(value = "quantity")
    public int quantity;
    @JsonProperty(value = "amount")
    public double amount;

    public ItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getSkuNum() {
        return skuNum;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSkuNum(String skuNum) {
        this.skuNum = skuNum;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
