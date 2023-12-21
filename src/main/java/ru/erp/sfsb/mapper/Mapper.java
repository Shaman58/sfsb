package ru.erp.sfsb.mapper;

import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.model.AbstractEntity;

import java.util.Collection;
import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(Collection<E> entity);
}