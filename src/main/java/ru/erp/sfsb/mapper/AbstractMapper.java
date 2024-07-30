package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.model.AbstractEntity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements Mapper<E, D> {

    private final ModelMapper mapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    AbstractMapper(ModelMapper mapper, Class<E> entityClass, Class<D> dtoClass) {
        this.mapper = mapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }

    @Override
    public List<D> toDto(Collection<E> list) {
        return Objects.isNull(list)
                ? null
                : list.stream().map(this::toDto).toList();
    }
}