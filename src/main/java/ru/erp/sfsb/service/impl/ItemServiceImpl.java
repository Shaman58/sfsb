package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.mapper.ItemMapper;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.ItemRepository;
import ru.erp.sfsb.service.ItemService;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ItemServiceImpl extends AbstractService<ItemDto, Item, ItemRepository, ItemMapper>
        implements ItemService {

    public ItemServiceImpl(ItemMapper mapper, ItemRepository repository) {
        super(mapper, repository, "Item");
        this.repository = repository;
    }

    @Override
    public List<ItemDto> getAllByTechnologyUser(Jwt jwt) {
        log.info("Looking items by user uuid of technology in DB");
        var uuid = jwt.getClaim("sub").toString();
        var entities = repository.getAllByTechnologyUser(uuid);
        return mapper.toDto(entities);
    }
}