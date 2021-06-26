package com.egen.ordermanagement.service;

import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.enums.OrderStatus;
import com.egen.ordermanagement.exceptions.ItemServiceException;
import com.egen.ordermanagement.model.Address;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.model.Orders;
import com.egen.ordermanagement.repository.AddressRepo;
import com.egen.ordermanagement.repository.ItemRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceImplTest {

    @Bean
    public ItemService getService(){return new ItemServiceImpl();}

    @Autowired
    ItemService itemService;

    @MockBean
    ItemRepo itemRepo;

    Item item;
    Orders orders;
    ItemDto itemDto;
    @Before
    public void setUp(){
        item  = new Item();
        itemDto = new ItemDto();
        itemDto.setId(8L).setItemPrice(20.5).setItemName("Monster Energy").setQuantityInStock(20);
        //Setting DTO values to entity to
        item.setId(itemDto.getId()).setItemPrice(itemDto.getItemPrice())
                .setItemName(itemDto.getItemName()).setQuantityInStock(itemDto.getQuantityInStock());
        orders = new Orders();
        orders.setId(8L);
        item.setOrders(orders);
        Mockito.when(itemRepo.save(item)).thenReturn(item);
        Mockito.when(itemRepo.findById(item.getId())).thenReturn(Optional.of(item));
    }
    @After
    public void cleanUp(){System.out.println("Done testing Address Service");}

    @Test
    public void createItem() {
        boolean new_item = itemService.createItem(itemDto);
        Assert.assertEquals("Failed to add item ",true,new_item);
    }

    @Test
    public void getItem() {
        Item findItem = itemService.getItem(item.getId());
        Assert.assertEquals("No items found",item,findItem);
    }

    //Provide Id which doesnt exist
    @Test(expected = ItemServiceException.class)
    public void noItemPresent() {
        Item findItem = itemService.getItem(13L);
    }

    @Test
    public void updateItem() {
        int currentStock = item.getQuantityInStock();
            itemService.updateItem(item.getId(),currentStock-3 );
        Assert.assertEquals("Failed to update order",currentStock-3,item.getQuantityInStock());
    }

    //Provide Id which doesnt exist
    @Test(expected = ItemServiceException.class)
    public void updateItemFailed() {
        int currentStock = item.getQuantityInStock();
        itemService.updateItem(13L,currentStock-3 );
    }
    @Test
    public void updateOrderIdInItem() {
    Item new_item = itemService.updateOrderIdInItem(item.getId(),orders);
    Assert.assertEquals("Failed to update Order id",item,new_item);
    }

    //Provide Id which doesnt exist
    @Test(expected = ItemServiceException.class)
    public void updateOrderIdInItemFailed() {
        Item new_item = itemService.updateOrderIdInItem(88L,orders);
    }
}