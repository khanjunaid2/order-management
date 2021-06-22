package com.egen.mapper;

import com.egen.dto.CustomerDTO;
import com.egen.model.Customer;

public class CustomerMapper {

    public CustomerDTO setCustomerDtoObject(Customer cust) {
        CustomerDTO obj=new CustomerDTO();
        obj.setEmail(cust.getEmail());
        obj.setFirstName(cust.getFirstName());
        obj.setId(cust.getId());
        obj.setLastName(cust.getLastName());
        obj.setOrders(cust.getOrders());
        obj.setPhoneNumber(cust.getPhoneNumber());
        return obj;
    }

    public Customer setCustomerObject(CustomerDTO cust) {
        Customer obj=new Customer();
        obj.setEmail(cust.getEmail());
        obj.setFirstName(cust.getFirstName());
        obj.setId(cust.getId());
        obj.setLastName(cust.getLastName());
        obj.setOrders(cust.getOrders());
        obj.setPhoneNumber(cust.getPhoneNumber());
        return obj;
    }
}
