package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.ItemMapper;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.ItemRepository;
import ru.erp.sfsb.service.ItemService;
import ru.erp.sfsb.service.TechnologyService;

import java.util.List;

import static java.lang.String.format;

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
    public List<ItemDto> getAll() {
        log.info("Looking all Items in DB");
        var items = repository.findAll().stream().map(mapper::toDto).toList();
        items.forEach(this::removeStore);
        return items;
    }

    @Override
    @Transactional
    public ItemDto get(@PathVariable Long id) {
        log.info("Looking Item with id={} in DB", id);
        var savedItemDto = mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no Item with id=%d in database", id)))));
        removeStore(savedItemDto);
        return savedItemDto;
    }

    @Override
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        log.info("Saving Item into DB");
        itemDto.setTechnology(technologyService.get(itemDto.getTechnology().getId()));
        var savedItemDto = mapper.toDto(repository.save(mapper.toEntity(itemDto)));
        removeStore(savedItemDto);
        return savedItemDto;
    }

    private void removeStore(ItemDto itemDto) {
        itemDto.getTechnology()
                .getSetups()
                .forEach(setup -> setup.getProductionUnit()
                        .getProductionArea()
                        .setStore(null));
    }
}