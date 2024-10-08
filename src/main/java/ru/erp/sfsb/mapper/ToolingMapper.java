package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.ToolingDto;
import ru.erp.sfsb.model.Tooling;

@Component
public class ToolingMapper extends AbstractMapper<Tooling, ToolingDto> {

    public ToolingMapper(ModelMapper mapper) {
        super(mapper, Tooling.class, ToolingDto.class);
    }
}