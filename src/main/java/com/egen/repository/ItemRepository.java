package com.egen.repository;

import com.egen.model.Customer;
import com.egen.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Items,Long> {
}
