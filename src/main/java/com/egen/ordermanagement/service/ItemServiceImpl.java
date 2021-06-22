package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.exceptions.ItemServiceException;
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
    public boolean createItem(ItemDto itemDto) {
        try {
             itemRepo.save(new Item(itemDto.getItemName(),itemDto.getItemPrice(),itemDto.getQuantityInStock()));
             return true;
        }catch (Exception ex){
            throw new ItemServiceException("Failed to add the item to the inventory",ex);
        }

    }

    @Transactional(readOnly = true)
    public Item getItem(Long id) {
        try {
            Optional<Item> item = itemRepo.findById(id);
            if(!item.isPresent())
                throw new Exception();
            else
            return item.get();
        }catch (Exception ex){
            throw new ItemServiceException("Sorry!! The item you are looking for is currently out of stock",ex);
        }
    }

    @Transactional
    public void updateItem(long id, int quantity) {
        try {
            Optional<Item> updatedItem = itemRepo.findById(id);
            if(updatedItem.isPresent()){
                updatedItem.get().setQuantityInStock(quantity);
            }else
                throw new Exception();

            itemRepo.save(updatedItem.get());
        }catch (Exception ex){
            throw new ItemServiceException("Failed to update the order",ex);
        }
    }

    @Transactional
    public void updateOrderIdInItem(long id, Orders order) {
        try {
            Optional<Item> updatedItem = itemRepo.findById(id);
            if(updatedItem.isPresent()){
                updatedItem.get().setOrders(order);
                itemRepo.save(updatedItem.get());
            }else
                throw new Exception();
        }catch (Exception ex){
            throw new ItemServiceException("Unable to place the order",ex);
        }
    }
}
