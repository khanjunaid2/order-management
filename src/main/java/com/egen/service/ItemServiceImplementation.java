package com.egen.service;

import com.egen.model.Items;
import com.egen.model.Order;
import com.egen.repo.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ItemServiceImplementation implements ItemService{

    @Autowired
    ItemsRepo itemsRepo;

    @Transactional
    public Items getItem(String id) {
        return itemsRepo.getItem(id);
    }

    @Transactional
    public void updateItem(String id, int quantity) {
        itemsRepo.updateItem(id,quantity);
    }

    @Transactional
    public void updateOrderIdInItem(String id, Order order) {
        itemsRepo.updateOrderIdItem(id,order);
    }
}
