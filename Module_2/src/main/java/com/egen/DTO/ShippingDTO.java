package com.egen.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class ShippingDTO implements Serializable{

    @Id
    private long Id;
    @JsonProperty(value = "ShippingId")
    private String shippingId;
    @JsonProperty(value = "ShippingMode")
    private String shippingMode;
    @JsonProperty(value = "ShippingCharge")
    private double shippingCharge;
}
