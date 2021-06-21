package com.egen.repo;

import com.egen.model.GroceryOrder;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface IOrderManagementRepo extends CrudRepository<GroceryOrder,String> {

}
