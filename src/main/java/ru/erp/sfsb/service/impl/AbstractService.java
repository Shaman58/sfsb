package ru.erp.sfsb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.Mapper;
import ru.erp.sfsb.model.AbstractEntity;
import ru.erp.sfsb.service.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
public abstract class AbstractService
        <D extends AbstractDto, E extends AbstractEntity, R extends JpaRepository<E, Long>, M extends Mapper<E, D>>
        implements Service<D> {

    M mapper;
    R repository;

    private final String entityName;

    public AbstractService(M mapper, R repository, String entityName) {
        this.mapper = mapper;
        this.repository = repository;
        this.entityName = entityName;
    }

    @Override
    public List<D> getAll() {
        log.info("Looking all {}s in DB", entityName);
        var entities = repository.findAll();
        return mapper.toDto(entities);
    }

    @Override
    public List<D> getAll(Pageable pageable) {
        log.info("Looking all {}s in DB", entityName);
        var entities = repository.findAll(pageable);
        return mapper.toDto(entities.stream().toList());
    }

    @Override
    public D get(Long id) {
        log.info("Looking {} with id={} in DB", entityName, id);
        return mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no %s with id=%d in database", entityName, id)))));
    }

    @Override
    public D save(D dto) {
        log.info("Saving {} into DB", entityName);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public D update(D dto) {
        log.info("Saving {} into DB", entityName);
        checkExistById(dto.getId());
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting {} with id {} in DB", entityName, id);
        checkExistById(id);
        repository.deleteById(id);
    }

    protected void checkExistById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Entity with id %d not found", id));
        }
    }
}