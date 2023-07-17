package ru.erp.sfsb.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.exception.EntityNotFoundException;
import ru.erp.sfsb.mapper.Mapper;
import ru.erp.sfsb.model.AbstractEntity;
import ru.erp.sfsb.service.Service;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

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
    @Transactional
    public List<D> getAll() {
        log.info("Looking all {}s in DB", entityName);
        var entities = repository.findAll();
        return entities.stream().map(mapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public List<D> getAll(Pageable pageable) {
        log.info("Looking all {}s in DB", entityName);
        var entities = repository.findAll(pageable);
        return entities.stream().map(mapper::toDto).collect(toList());
    }

    @Override
    @Transactional
    public D get(@PathVariable Long id) {
        log.info("Looking {} with id={} in DB", entityName, id);
        return mapper.toDto((repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(format("There is no %s with id=%d in database", entityName, id)))));
    }

    @Override
    @Transactional
    public D save(D dto) {
        log.info("Saving {} into DB", entityName);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public D update(D dto) {
        log.info("Saving {} into DB", entityName);
        get(dto.getId());
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting {} with id {} in DB", entityName, id);
        get(id);
        repository.deleteById(id);
    }
}