package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.mapper.ItemMapper;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.ItemRepository;
import ru.erp.sfsb.service.ItemService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class ItemServiceImpl extends AbstractService<ItemDto, Item, ItemRepository, ItemMapper>
        implements ItemService {

    private final ItemMapper mapper;
    private final ItemRepository repository;

    public ItemServiceImpl(ItemMapper mapper, ItemRepository repository) {
        super(mapper, repository, "Item");
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<ItemDto> getOrderItems(Long id) {
        log.info("Get Order {} Items from DB", id);
        return repository.getItemsByOrderId(id).stream().map(mapper::toDto).collect(toList());
    }
}