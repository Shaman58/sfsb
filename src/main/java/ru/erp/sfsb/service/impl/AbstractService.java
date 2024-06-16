package ru.erp.sfsb.service.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import ru.erp.sfsb.LogTag;
import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.Mapper;
import ru.erp.sfsb.model.AbstractEntity;
import ru.erp.sfsb.repository.EntityRepository;
import ru.erp.sfsb.service.Service;

import java.util.List;

@Slf4j
@Getter
public abstract class AbstractService
        <D extends AbstractDto, E extends AbstractEntity, R extends EntityRepository<E>, M extends Mapper<E, D>>
        implements Service<D> {

    M mapper;
    R repository;

    private final String entityName;
    private final LogTag logTag;

    public AbstractService(M mapper, R repository, String entityName, LogTag logTag) {
        this.mapper = mapper;
        this.repository = repository;
        this.entityName = entityName;
        this.logTag = logTag;
    }

    @Override
    public List<D> getAll() {
        log.info("[{}] Поиск всех сущностей типа {} в БД", logTag, entityName);
        var entities = repository.findAllByDeletedIsFalse();
        return mapper.toDto(entities);
    }

    @Override
    public List<D> getAll(Pageable pageable) {
        log.info("[{}] Поиск всех сущностей типа {} в БД постранично", logTag, entityName);
        var entities = repository.findAllByDeletedIsFalse(pageable);
        return mapper.toDto(entities.stream().toList());
    }

    @Override
    public D get(Long id) {
        log.info("[{}] Поиск сушности типа {} с id={} в БД", logTag, entityName, id);
        return mapper.toDto((repository.findById(id).orElseThrow(
                () -> getEntityWithIdNotFoundException(id))));
    }

    @Override
    public D save(D dto) {
        log.info("[{}] Сохранение сущности типа {} в БД", logTag, entityName);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public D update(D dto) {
        log.info("[{}] Обновление сущности типа {} в БД", logTag, entityName);
        checkExistById(dto.getId());
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        log.info("[{}] Удаление сущности типа {} с id={} из БД", logTag, entityName, id);
        checkExistById(id);
        repository.setDeletedById(id);
//        E entity = repository.findById(id).orElseThrow(
//                () -> getEntityWithIdNotFoundException(id));
//        entity.setDeleted(true);
//        repository.save(entity);
    }

    protected void checkExistById(Long id) {
        if (!repository.existsById(id)) {
            throw getEntityWithIdNotFoundException(id);
        }
    }

    protected EntityNotFoundException getEntityWithIdNotFoundException(Long id) {
        return new EntityNotFoundException(
                String.format("[%s] Сушность типа %s с id=%d не найдена", logTag, entityName, id));
    }
}