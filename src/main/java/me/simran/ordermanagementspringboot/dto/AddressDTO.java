package me.simran.ordermanagementspringboot.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.simran.ordermanagementspringboot.enums.AddressType;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {
    private Long id;

    private String city;

    private String state;

    private Integer zip;

    private AddressType addressType;

    private String addressLine1;

    private String addressLine2;
}
