package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.model.Item;

@Component
public class ItemMapper extends AbstractMapper<Item, ItemDto> {

    public ItemMapper() {
        super(Item.class, ItemDto.class);
    }
}