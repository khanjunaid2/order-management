package com.egen.ordermanagement.controller;

import com.egen.ordermanagement.dto.ItemDto;
import com.egen.ordermanagement.response.Response;
import com.egen.ordermanagement.response.ResponseMetadata;
import com.egen.ordermanagement.response.StatusMessage;
import com.egen.ordermanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * This function is used to add a new item to the db
     * @return -returns HTTP status as created and the details of the new item
     */
    @PostMapping(value="/additem",consumes = "application/json",produces = "application/json")
    public Response<Boolean> createItem(@RequestBody ItemDto itemDto){
        return itemService.createItem(itemDto) == Boolean.TRUE ?
                Response.<Boolean>builder().meta(ResponseMetadata.builder().statusCode(201)
                        .statusMessage(StatusMessage.CREATED.name())
                        .build()).data(true).build()
                :
                Response.<Boolean>builder().meta(ResponseMetadata.builder().statusCode(404)
                        .statusMessage(StatusMessage.EQUIPMENT_SERVICE_INTERNAL_ERROR.name())
                        .build()).data(false).build();
    }
}
