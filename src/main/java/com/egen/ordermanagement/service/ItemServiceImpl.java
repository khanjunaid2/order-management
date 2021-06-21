package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.exceptions.ItemNotFoundException;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepo itemRepo;

    @Transactional
    public Item createItem(ItemDto itemDto) {
        return itemRepo.save(new Item(itemDto.getItemName(),itemDto.getItemPrice(),itemDto.getQuantityInStock()));
    }

    @Transactional(readOnly = true)
    public Item getItem(Long id) {
        Optional<Item> item = itemRepo.findById(id);
        if(!item.isPresent())
            throw new ItemNotFoundException("Sorry!! The item you are looking for is currently out of stock");
        return item.get();
    }

    @Transactional
    public void updateItem(long id, int quantity) {
        Optional<Item> updatedItem = itemRepo.findById(id);
        if(updatedItem.isPresent()){
            updatedItem.get().setQuantityInStock(quantity);
        }
        itemRepo.save(updatedItem.get());
    }

    @Transactional
    public void updateOrderIdInItem(long id, Orders order) {
        Optional<Item> updatedItem = itemRepo.findById(id);
        if(updatedItem.isPresent()){
            updatedItem.get().setOrders(order);
        }
        itemRepo.save(updatedItem.get());
    }
}
