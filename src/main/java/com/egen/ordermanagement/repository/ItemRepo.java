package com.egen.ordermanagement.repository;

import com.egen.ordermanagement.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends CrudRepository<Item,Long> {
}
