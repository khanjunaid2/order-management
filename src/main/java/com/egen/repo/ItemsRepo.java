package com.egen.repo;

import com.egen.model.Items;
import com.egen.model.Order;

public interface ItemsRepo {
    Items getItem(String id);
    void updateItem(String id, int quantity);
    void updateOrderIdItem(String id, Order order);

}
