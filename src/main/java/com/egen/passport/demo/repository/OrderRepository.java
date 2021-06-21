package com.egen.passport.demo.repository;

import com.egen.passport.demo.entity.Customer;
import com.egen.passport.demo.entity.CustomerOrder;
import com.egen.passport.demo.entity.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findAll();
    CustomerOrder findCustomerOrderByCustomerId(Long id);
    CustomerOrder findCustomerOrderById(Long id);
    CustomerOrder findCustomerOrderByDeliveryType(DeliveryType deliveryType);
}
