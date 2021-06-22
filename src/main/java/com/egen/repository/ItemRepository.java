package com.egen.repository;

import com.egen.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,String> {
}
