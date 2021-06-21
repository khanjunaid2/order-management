package com.egen.passport.demo.dto;

public class CustomerDTO {

    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerDTO setId(long id) {
        this.id = id;
        return this;
    }

    public CustomerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
