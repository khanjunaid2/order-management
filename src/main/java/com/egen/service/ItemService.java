package com.egen.service;

import com.egen.model.Items;
import com.egen.model.Order;

public interface ItemService {
    Items getItem(String id);
    void updateItem(String id, int quantity);
    void updateOrderIdInItem(String id, Order order);
}
