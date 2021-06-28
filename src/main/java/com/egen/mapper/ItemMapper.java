package com.egen.mapper;

import com.egen.dto.ItemDto;
import com.egen.model.entity.Item;
import com.egen.model.entity.Orders;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toItemDto(Item item);
    Item toItem(ItemDto itemDto);
    List<ItemDto> toItemDtos(List<Item> items);
    List<Item> toItems(List<ItemDto> itemsDto);

}
