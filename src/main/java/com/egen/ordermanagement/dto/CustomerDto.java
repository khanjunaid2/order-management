package com.egen.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto implements Serializable {

    private Long id;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    public CustomerDto setId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerDto setEmail(String email) {
        this.email = email;
        return this;

    }

    public CustomerDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;

    }

    public CustomerDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
