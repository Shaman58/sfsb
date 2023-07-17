package ru.erp.sfsb.mapper;

import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.TechnologyDto;
import ru.erp.sfsb.model.Technology;

@Component
public class TechnologyMapper extends AbstractMapper<Technology, TechnologyDto> {

    public TechnologyMapper() {
        super(Technology.class, TechnologyDto.class);
    }
}