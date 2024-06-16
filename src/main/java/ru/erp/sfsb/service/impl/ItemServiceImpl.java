package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.erp.sfsb.dto.ItemDto;
import ru.erp.sfsb.mapper.ItemMapper;
import ru.erp.sfsb.model.Item;
import ru.erp.sfsb.repository.repos.ItemEntityRepository;
import ru.erp.sfsb.service.ItemService;

import java.util.List;

import static ru.erp.sfsb.LogTag.ITEM_SERVICE;

@Service
@Slf4j
@Transactional
public class ItemServiceImpl extends AbstractService<ItemDto, Item, ItemEntityRepository, ItemMapper>
        implements ItemService {

    public ItemServiceImpl(ItemMapper mapper, ItemEntityRepository repository) {
        super(mapper, repository, "Item", ITEM_SERVICE);
        this.repository = repository;
    }

    @Override
    public List<ItemDto> getAllByTechnologyUser(Jwt jwt) {
        log.info("[{}] Поиск всех позиций по профилю пользователя в БД", getLogTag());
        var uuid = jwt.getClaim("sub").toString();
        var entities = repository.getAllByTechnologyUser(uuid);
        return mapper.toDto(entities);
    }
}