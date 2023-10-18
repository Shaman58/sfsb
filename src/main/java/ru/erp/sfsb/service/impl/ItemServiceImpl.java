package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.mapper.ItemMapper;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.ItemRepository;
import ru.erp.sfsb.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl extends AbstractService<ItemDto, Item, ItemRepository, ItemMapper>
        implements ItemService {

    public ItemServiceImpl(ItemMapper mapper, ItemRepository repository) {
        super(mapper, repository, "Item");
        this.mapper = mapper;
        this.repository = repository;
    }
}