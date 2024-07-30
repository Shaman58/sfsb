package ru.erp.sfsb.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.erp.sfsb.dto.SpecialToolDto;
import ru.erp.sfsb.model.SpecialTool;

@Component
public class SpecialToolMapper extends AbstractMapper<SpecialTool, SpecialToolDto> {

    public SpecialToolMapper(ModelMapper mapper) {
        super(mapper, SpecialTool.class, SpecialToolDto.class);
    }
}