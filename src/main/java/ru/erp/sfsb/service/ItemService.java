package ru.erp.sfsb.service;

import ru.erp.sfsb.dto.ItemDto;

import java.util.List;

public interface ItemService extends Service<ItemDto> {

    List<ItemDto> getOrderItems(Long id);
}