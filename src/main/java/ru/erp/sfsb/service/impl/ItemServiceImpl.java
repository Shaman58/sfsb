package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.mapper.ItemMapper;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.ItemRepository;
import ru.erp.sfsb.service.ItemService;
import ru.erp.sfsb.service.TechnologyService;

@Service
@Slf4j
public class ItemServiceImpl extends AbstractService<ItemDto, Item, ItemRepository, ItemMapper>
        implements ItemService {

    private final ItemMapper mapper;
    private final ItemRepository repository;
    private final TechnologyService technologyService;

    public ItemServiceImpl(ItemMapper mapper, ItemRepository repository, TechnologyService technologyService) {
        super(mapper, repository, "Item");
        this.mapper = mapper;
        this.repository = repository;
        this.technologyService = technologyService;
    }

    @Override
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        log.info("Saving Item into DB");
        itemDto.setTechnology(technologyService.get(itemDto.getTechnology().getId()));
        return mapper.toDto(repository.save(mapper.toEntity(itemDto)));
    }
}