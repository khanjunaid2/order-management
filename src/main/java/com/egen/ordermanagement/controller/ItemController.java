package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.model.Item;
import com.egen.ordermanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * This function is used to add a new item to the db
     * @return -returns HTTP status as created and the details of the new item
     */
    @RequestMapping(method = RequestMethod.POST,value = "/additem",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto){
        return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
    }
}
