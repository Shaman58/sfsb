package ru.erp.sfsb.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.erp.sfsb.dto.AbstractDto;
import ru.erp.sfsb.model.AbstractEntity;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> {

    @Autowired
    protected ModelMapper mapper;

    protected Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    protected void mapSpecificFields(E source, D destination) {
    }

    protected void mapSpecificFields(D source, E destination) {
    }
}