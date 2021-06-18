package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;

public interface ItemService {
    Item createItem(ItemDto itemDto);
    Item getItem(Long id);
    void updateItem(long id,int quantity);
    void updateOrderIdInItem(long id, Orders order);
}
